package com.uber.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uber.model.WalletTransaction;
import com.uber.repositories.WalletTransactionRepository;
import com.uber.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

	private final WalletTransactionRepository walletTransactionRepository;
	private final ModelMapper modelMapper;

	@Override
	public void createNewWalletTransaction(WalletTransaction walletTransaction) {
		walletTransactionRepository.save(walletTransaction);
	}

}
