package com.company.bankapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Builder
@Table(name = "account_details")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acc_no", length = 7)
	private Integer accountNo;

	@Column(name = "acc_holder", length = 30, nullable = false)
	private String holderName;

	@Column(name = "acc_balance", length = 20, nullable = false)
	private Long accountBalance;

	@Column(name = "holder_email", length = 40, unique = true, nullable = false)
	private String holderEmail;

}
