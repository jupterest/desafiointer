package br.com.di.desafiointer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag ("TestesClasseManipuladoraNumeros")
public class NumberUtilTest {
	
	@Test
	@DisplayName("testa geracao de inteiro positivo")
	public void gerarInteiroPositivoAleatorio() {
		Integer id1 = NumberUtil.generateId();
		Integer id2 = NumberUtil.generateId();
		Integer id3 = NumberUtil.generateId();
		Integer id4 = NumberUtil.generateId();
		Integer id5 = NumberUtil.generateId();
		Integer id6 = NumberUtil.generateId();;
		
		Set<Integer> ids = new HashSet<Integer>(Arrays.asList(id1,id2,id3,id4,id5,id6));
		
		assertEquals(6, ids.size());
		
		for(Integer i:ids) {
			assertTrue(i>0);
		}
		
		
	}


}
