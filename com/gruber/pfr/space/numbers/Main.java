package com.gruber.pfr.space.numbers;

import java.util.Random;

import com.gruber.pfr.space.numbers.integer.ModuloClass;
import com.gruber.pfr.space.numbers.integer.ModuloQuotient;
import com.gruber.pfr.space.numbers.rational.RationalNumber;
import com.gruber.pfr.space.numbers.real.RealNumber;

public class Main {

	public static void main(String[] args) {

		RationalToRealInjection rat2real = new RationalToRealInjection();

		System.out.println(rat2real.checkAdditionCompatibility(1000));

		Random rand = new Random(System.currentTimeMillis());

		for (int i = 0; i < 100; i++) {

			float test = rand.nextFloat() * rand.nextInt();
			RationalNumber rat = new RationalNumber(test);
			RealNumber real = (RealNumber) rat2real.getImage(rat);
			System.out.println(rat.getBase() + " " + real.getBase());
		}

		int length = 12;
		ModuloQuotient quot = new ModuloQuotient(length);

		for (int i = 0; i < 100; i++) {
			// try first without implementation of getRandom in ModuloQuotient
			// -> due to number overflow, addition and multiplication are not
			// correct
			ModuloClass cl1 = quot.getRandomElement();
			ModuloClass cl2 = quot.getRandomElement();
			ModuloClass clplus = (ModuloClass) quot.add(cl1, cl2);
			ModuloClass cltimes = (ModuloClass) quot.multiply(cl1, cl2);

			System.out.println(
					cl1.getIntBase() + " " + cl2.getIntBase() + " " + clplus.getIntBase() + " " + cltimes.getIntBase());
		}
	}
}
