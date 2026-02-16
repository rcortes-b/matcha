package me.rcortesb.interaction.service.impl;

import java.util.Optional;

import me.rcortesb.interaction.domain.dto.ReportDTO;
import me.rcortesb.interaction.domain.entity.BlockEntity;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReport(String userId, ReportDTO report) {
		// After report i have to block the user
		
	}

	public boolean userIsBlocked(UserTargetId id) {
		Optional<BlockEntity> block = blockRepository.findById(id);
		return block.isEmpty() ? false : true;
	}
	
}
