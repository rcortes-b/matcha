package me.rcortesb.interaction.service.impl;

import org.springframework.stereotype.Service;

import me.rcortesb.interaction.domain.dto.ReportDTO;
import me.rcortesb.interaction.service.InteractionService;

@Service
public class InteractionServiceImpl implements InteractionService {

	@Override
	public void handleBlock(String userId, String targetId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleLike(String userId, String targetId) {
		/*	1. Comprobar que el User con targetId existe
			2. Guardar el like en la BBDD si no esta ya guardado (Comprobar que no este block)
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

	@Override
	public void handleReport(String userId, ReportDTO report) {
		// TODO Auto-generated method stub
		/* Importante que una vez reportado el usuario, se considere como usuario bloqueado */
		
	}
	
}
