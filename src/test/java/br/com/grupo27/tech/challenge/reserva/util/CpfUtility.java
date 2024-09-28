package br.com.grupo27.tech.challenge.reserva.util;

import java.util.Random;

public class CpfUtility {

    public static String generateCpf() {
        var random = new Random();
        var cpf = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            cpf.append(random.nextInt(10));
        }
        cpf.append(calculateCheckDigit(cpf.toString(), 10)); // First check digit
        cpf.append(calculateCheckDigit(cpf.toString(), 11)); // Second check digit
        return cpf.toString();
    }

    private static char calculateCheckDigit(String base, int weight) {
        var total = 0;
        for (int i = 0; i < base.length(); i++) {
            total += Character.getNumericValue(base.charAt(i)) * weight--;
        }
        var remainder = total % 11;
        return (remainder < 2) ? '0' : Character.forDigit(11 - remainder, 10);
    }

    public static String formatCpf(String cpf) {
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }

}