package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CategoriaTest {
	
	@Test
    public void testAccountEmptyConstructor(){
        CategoriaEntity categoria= new CategoriaEntity();
        assertNotNull(categoria);
    }
	
	@Test
	public void testGetId() {
		CategoriaEntity categoria = new CategoriaEntity();
		categoria.setIdCategoria(1);
        assertEquals(1,categoria.getIdCategoria());
	}
	
	@Test
	public void testGetNome() {
		CategoriaEntity categoria= new CategoriaEntity();
		categoria.setNome("Matematica 1");
		assertEquals("Matematica 1", categoria.getNome());
	}
	
	@Test
	public void testGetDescrizione() {
		CategoriaEntity categoria= new CategoriaEntity();
		categoria.setDescrizione("studio di funzioni");
		assertEquals("studio di funzioni", categoria.getDescrizione());
	}
	
	@Test
	public void testGetAmbito() {
		CategoriaEntity categoria= new CategoriaEntity();
		categoria.setAmbito("informatiche");
		assertEquals("informatiche",categoria.getAmbito());
	}
	
	@Test
	public void testToString() {
		CategoriaEntity categoria= new CategoriaEntity();
		String expectedValue= "Categoria [idCategoria=" + categoria.idCategoria + ", nome=" + categoria.nome + ", descrizione=" + categoria.descrizione
				+ ", ambito=" + categoria.ambito +  "]";
		
		String actualValue= categoria.toString();
		
		assertEquals(expectedValue,actualValue);
	}
	
}








