package com.example;

import java.util.List;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dominio.Alumno;
import com.example.repositorio.AlumnoRepositorio;

@EnableAutoConfiguration
@Controller
public class DemoApplication {

	AlumnoRepositorio repositorio = new AlumnoRepositorio();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/alumnos")
	@ResponseBody
	public List<Alumno> alumnos(String ordenarPor) {
		
		if(ordenarPor.equals("Promedio")){
			List<Alumno> lista = repositorio.listar();
			for(int i = 0; i < lista.size(); i++){
				for(int j = 0; j<i;j++){
					if(lista.get(j).promedio > lista.get(j+1).promedio){
						Collections.swap(lista, j, j+1);
					}
				}
			}
			return lista;
		}
		if(ordenarPor.equals("Nombres")){
			List<Alumno> lista = repositorio.listar();
			for(int i = 0; i < lista.size(); i++){
				for(int j = 0; j<i;j++){
					if(lista.get(j).nombres.compareTo(lista.get(j+1).nombres) > 0){
						Collections.swap(lista,j,j+1);
					}
				}
			}
			return lista;
		}
		if(ordenarPor.equals("ApellidoPaterno")){
			List<Alumno> lista = repositorio.listar();
			for(int i = 0; i < lista.size(); i++){
				for(int j = 0; j<i;j++){
					if(lista.get(j).apellidoPaterno.compareTo(lista.get(j+1).apellidoPaterno) > 0){
						Collections.swap(lista,j,j+1);
					}
				}
			}
			return lista;
		}
		return repositorio.listar();
	}
}
