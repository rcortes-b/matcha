package me.rcortesb.interaction.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import me.rcortesb.common.InteractionEventDTO;
import me.rcortesb.interaction.domain.entity.LikeEntity;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;
import me.rcortesb.interaction.repository.LikeRepository;
import me.rcortesb.interaction.service.BlockService;
import me.rcortesb.interaction.service.MatchService;
import me.rcortesb.interaction.service.events.MatchEventProducer;
import me.rcortesb.interaction.service.grpc.UserValidClient;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
	final private LikeRepository likeRepository;
	final private BlockService blockService;
	final private UserValidClient userValidClient;
	final private MatchEventProducer matchEventProducer;
	

	public MatchServiceImpl(UserValidClient userValidClient,
									LikeRepository likeRepository,
									BlockService blockService,
									MatchEventProducer matchEventProducer) {
		this.userValidClient = userValidClient;
		this.likeRepository = likeRepository;
		this.blockService = blockService;
		this.matchEventProducer = matchEventProducer;
	}

	/*Maybe I should check that the user hasn't blocked the targetId (edge case doing a custom request) */
	@Override
	public void handleLike(String userId, String targetId) {
		if (!userValidClient.isUserValid(targetId))
			throw new RuntimeException("CREATE A CUSTOM EXCEPTION HERE: handleLike");
		UserTargetId compositeKey = new UserTargetId(userId, targetId);
		if (blockService.hasBlockRelation(compositeKey))
			throw new RuntimeException("CREATE A CUSTOM EXCEPTION HERE: handleLike --- 2");
		saveLike(compositeKey);
		compositeKey.reverseIds();
		
		boolean isMatch = userIsLiked(compositeKey);
		if (isMatch) {
			handleMatch(userId, targetId, false);
		} else {
			matchEventProducer.sendLikeEvent(new InteractionEventDTO(userId, targetId));
		}
	}

	@Override
	public void handleMatch(String user1, String user2, boolean doCheck) {
		if (doCheck) {
			UserTargetId id = new UserTargetId(user1, user2);
			boolean isMatch = userIsLiked(id);
			if (!isMatch)
				throw new RuntimeException("handleMatch Exception --- Create Custom Exception");
			id.reverseIds();
			saveLike(id);
		}
		matchEventProducer.sendMatchEvent(new InteractionEventDTO(user1, user2));
	}

	@Override
	public void deleteLikes(String user1, String user2) {
		UserTargetId id = new UserTargetId(user1, user2);
		LikeEntity like = LikeEntity.builder()
									.id(id)
									.build();
		deleteLike(like);
		id.reverseIds();
		like.setId(id);
		deleteLike(like);
	}

	private boolean userIsLiked(UserTargetId id) {
		Optional<LikeEntity> like = likeRepository.findById(id);
		return like.isEmpty() ? false : true;
	}

	private LikeEntity saveLike(UserTargetId id) {
		return likeRepository.save(LikeEntity.builder()
					  					  	 .id(id)
					  					  	 .build());
	}

	private void deleteLike(LikeEntity like) {
		likeRepository.delete(like);
	}
}
