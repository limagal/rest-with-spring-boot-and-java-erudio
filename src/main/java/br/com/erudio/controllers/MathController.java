package br.com.erudio.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne")
            String numberOne,
            @PathVariable("numberTwo")
            String numberTwo){

        return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
    }
    // localhost:8080/math/subtraction/3/5
    // localhost:8080/math/multiplication/3/5
    // localhost:8080/math/division/3/5
    // localhost:8080/math/media/3/5
    // localhost:8080/math/square/3

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isBlank()) {
            return null;
        }

        // Troca vírgula por ponto, pois Double.parseDouble só aceita ponto
        String number = strNumber.replace(",", ".");

        // Verifica se é realmente número válido
        if (!isNumeric(number)) {
            return null;
        }

        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;

        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
