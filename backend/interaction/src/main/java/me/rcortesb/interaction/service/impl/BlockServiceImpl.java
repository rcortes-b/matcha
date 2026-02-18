package me.rcortesb.interaction.service.impl;

import java.util.Optional;

import me.rcortesb.interaction.domain.dto.ReportDTO;
import me.rcortesb.interaction.domain.entity.BlockEntity;
import me.rcortesb.interaction.domain.entity.ReportEntity;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;
import me.rcortesb.interaction.repository.BlockRepository;
import me.rcortesb.interaction.repository.ReportRepository;
import me.rcortesb.interaction.service.BlockService;
import me.rcortesb.interaction.service.grpc.UserValidClient;

public class BlockServiceImpl implements BlockService {
	final private BlockRepository blockRepository;
	final private ReportRepository reportRepository;
	final private UserValidClient userValidClient;

	public BlockServiceImpl(BlockRepository blockRepository,
							ReportRepository reportRepository,
							UserValidClient userValidClient) {
		this.blockRepository = blockRepository;
		this.reportRepository = reportRepository;
		this.userValidClient = userValidClient;
	}


	@Override
	public void handleBlock(String userId, String targetId) {
		if (!userValidClient.isUserValid(targetId))
			throw new RuntimeException("CREATE A CUSTOM EXCEPTION HERE: handleBlock");
		UserTargetId id = new UserTargetId(userId, targetId);
		blockUser(BlockEntity.builder()
							 .id(id)
							 .build());
	}

	@Override
	public void handleReport(String userId, ReportDTO report) {
		// After report i have to block the user
		if (!userValidClient.isUserValid(report.targetId()))
			throw new RuntimeException("CREATE A CUSTOM EXCEPTION HERE: handleReport");
		UserTargetId id = new UserTargetId(userId, report.targetId());
		reportRepository.save(ReportEntity.builder()
										  .reason(report.reason())
										  .id(id)
										  .build());
		blockUser(BlockEntity.builder()
							.id(id)
							.build());
	}

	@Override
	public boolean userIsBlocked(UserTargetId id) {
		Optional<BlockEntity> block = blockRepository.findById(id);
		return block.isEmpty() ? false : true;
	}

	@Override
	public boolean hasBlockRelation(UserTargetId id) {
		id.reverseIds();
		Optional<BlockEntity> block1 = blockRepository.findById(id);
		id.reverseIds();
		Optional<BlockEntity> block2 = blockRepository.findById(id);
		if (block1.isEmpty() && block2.isEmpty())
			return false;
		return true;
	}

	private BlockEntity blockUser(BlockEntity block) {
		return blockRepository.save(block);
	}
	
}
