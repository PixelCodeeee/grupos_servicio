package com.pixelcode.grupo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Microservicio de Grupos
 * Gestiona grupos/clases académicas
 * 
 * Migrado a:
 * - Clever Cloud MySQL (base de datos compartida)
 * - Eureka Service Discovery
 * - API Gateway compatible
 * 
 * @version 1.0 - MySQL & Eureka Integration
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GrupoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrupoServiceApplication.class, args);
		
		System.out.println("\n╔════════════════════════════════════════════════════════════╗");
		System.out.println("║                                                            ║");
		System.out.println("║          MICROSERVICIO DE GRUPOS INICIADO                  ║");
		System.out.println("║                                                            ║");
		System.out.println("║  Servicio: grupo-service                                   ║");
		System.out.println("║  Puerto: 8083                                              ║");
		System.out.println("║  Database: Clever Cloud MySQL                              ║");
		System.out.println("║  Eureka: http://localhost:8761                             ║");
		System.out.println("║  API Base: http://localhost:8083/api/grupos                ║");
		System.out.println("║                                                            ║");
		System.out.println("║  Endpoints disponibles:                                    ║");
		System.out.println("║  • GET    /api/grupos                [Listar todos]        ║");
		System.out.println("║  • GET    /api/grupos/activos        [Solo activos]        ║");
		System.out.println("║  • GET    /api/grupos/{id}           [Por ID]              ║");
		System.out.println("║  • GET    /api/grupos/codigo/{codigo} [Por código]        ║");
		System.out.println("║  • GET    /api/grupos/division/{id}  [Por división]        ║");
		System.out.println("║  • GET    /api/grupos/division/{id}/activos                ║");
		System.out.println("║  • POST   /api/grupos                [Crear grupo]         ║");
		System.out.println("║  • PUT    /api/grupos/{id}           [Actualizar]          ║");
		System.out.println("║  • DELETE /api/grupos/{id}           [Eliminar lógico]     ║");
		System.out.println("║  • PATCH  /api/grupos/{id}/reactivar [Reactivar]          ║");
		System.out.println("║  • DELETE /api/grupos/{id}/permanente [Eliminar físico]   ║");
		System.out.println("║                                                            ║");
		System.out.println("║  Vía API Gateway:                                          ║");
		System.out.println("║  http://localhost:8080/grupo-service/api/grupos            ║");
		System.out.println("║                                                            ║");
		System.out.println("║  📊 11 Endpoints Total - Division Reference ✓              ║");
		System.out.println("║  🎓 UTEQ - Sistema de Asistencias 2025                     ║");
		System.out.println("║                                                            ║");
		System.out.println("╚════════════════════════════════════════════════════════════╝\n");
	}

}
