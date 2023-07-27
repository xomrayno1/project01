package com.tamnc.objects;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Money {
	public static final Money ZERO = new Money(BigDecimal.ZERO);
	
	private final BigDecimal amount;
	
	public boolean isGreaterThanZero() {
		return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
	}
	
	public boolean isGreaterThan(Money money) {
		return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
	}
	
	public Money add(Money money) {
		return new Money(this.amount.add(money.getAmount()));
	}
	
	public Money substract(Money money) {
		return new Money(setScale(this.amount.subtract(money.getAmount())));
	}
	
	public Money multiply(int multiplier) {
		return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Money money = (Money) obj;
		return amount.equals(money.getAmount());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}
	
	public BigDecimal setScale(BigDecimal input) {
		return input.setScale(2, RoundingMode.HALF_EVEN);
	}
	
}
