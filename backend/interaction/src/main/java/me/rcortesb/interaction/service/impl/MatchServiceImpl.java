package me.rcortesb.interaction.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import me.rcortesb.interaction.domain.entity.LikeEntity;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;
import me.rcortesb.interaction.repository.LikeRepository;
import me.rcortesb.interaction.service.BlockService;
import me.rcortesb.interaction.service.MatchService;
import me.rcortesb.interaction.service.grpc.UserValidClient;
import me.rcortesb.interaction.utils.InteractionUtils;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
	final private LikeRepository likeRepository;
	final private BlockService blockService;
	final private UserValidClient userValidClient;

	public MatchServiceImpl(UserValidClient userValidClient,
									LikeRepository likeRepository,
									BlockService blockService
	) {
		this.userValidClient = userValidClient;
		this.likeRepository = likeRepository;
		this.blockService = blockService;
	}

	@Override
	public void handleLike(String userId, String targetId) {
		if (!userValidClient.isUserValid(targetId))
			throw new RuntimeException("CREATE A CUSTOM EXCEPTION HERE: handleLike");
		UserTargetId compositeKey = InteractionUtils.getInteractionUtils().createCompositeKey(userId, targetId);
		if (!blockService.userIsBlocked(compositeKey)) {
			LikeEntity like = new LikeEntity();
			like.setId(compositeKey);
			likeRepository.save(like);
		}
		/*	1. Comprobar que el User con targetId existe - DONE
			2. Guardar el like en la BBDD si no esta ya guardado (Comprobar que no este block) - DONE
			3. Comprobar si se ha hecho match y enviar evento:
				3.1. Si no hay match, enviar LikeEvent
				3.2. Si hay match, enviar MatchEvent
		*/
		
	}

	@Override
	public void handleMatch(String user1, String user2, boolean doCheck) {
		// TODO Auto-generated method stub
		/*  
			El doCheck sirve como opcion para filtrar, depende de si handleMatch se llamada desde un endpoint
			o desde el handleLike. handleLike == doCheck=false && handleMatch == doCheck=true
		*/
	}
}
