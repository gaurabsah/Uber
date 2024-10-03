package com.uber.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.uber.model.enums.TransactionMethod;
import com.uber.model.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(indexes = { @Index(name = "idx_wallet_transaction_wallet", columnList = "wallet_id"),
		@Index(name = "idx_wallet_transaction_ride", columnList = "ride_id") })
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	private TransactionType transactionType;

	private TransactionMethod transactionMethod;

	@ManyToOne
	private Ride ride;

	private String transactionId;

	@ManyToOne
	private Wallet wallet;

	@CreationTimestamp
	private LocalDateTime timeStamp;
}
