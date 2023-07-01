package com.openpay.apirestopenpay.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	
	public static String getFecha() {
		// Obtener la fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Definir el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Formatear y mostrar la fecha y hora actual
        String fechaHoraFormateada = fechaHoraActual.format(formatter);
        return fechaHoraFormateada;
	}
}
