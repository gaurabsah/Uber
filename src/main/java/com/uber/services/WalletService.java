package com.uber.services;

import com.uber.model.Ride;
import com.uber.model.User;
import com.uber.model.Wallet;
import com.uber.model.enums.TransactionMethod;

public interface WalletService {

	Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride,
			TransactionMethod transactionMethod);

	Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride,
			TransactionMethod transactionMethod);

	void withdrawAllMyMoneyFromWallet();

	Wallet findWalletById(Long walletId);

	Wallet createNewWallet(User user);

	Wallet findByUser(User user);

}
