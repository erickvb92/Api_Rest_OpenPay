package com.openpay.apirestopenpay.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openpay.apirestopenpay.dao.BitacoraRepository;
import com.openpay.apirestopenpay.entitys.Bitacora;

//defining the business logic
@Service
public class BitacoraService {
@Autowired
BitacoraRepository bitacoraRepository;
//getting all Bitacora records
public List<Bitacora> getAllBitacora() 
{
List<Bitacora> bitacoras = new ArrayList<Bitacora>();
bitacoraRepository.findAll().forEach(Bitacora -> bitacoras.add(Bitacora));
return bitacoras;
}

	public Bitacora getBitacoraById(int id) {
		return bitacoraRepository.findById(id).get();
	}

	public void saveOrUpdate(Bitacora bitacora) {
		bitacoraRepository.save(bitacora);
	}

	public void delete(int id) {
		bitacoraRepository.deleteById(id);
	}
}