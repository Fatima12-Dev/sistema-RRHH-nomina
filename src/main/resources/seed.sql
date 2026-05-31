INSERT INTO empleados (nombre, cargo, departamento, salario_base, fecha_ingreso)
SELECT * FROM (
  SELECT 'Ana Castro','Coordinador','Operaciones',1550.0,'2021-03-24'
  UNION ALL
  SELECT 'Daniela Ortiz','Analista','TI',900.0,'2018-02-07'
  UNION ALL
  SELECT 'Roberto Lopez','Asistente RRHH','RRHH',1400.0,'2024-04-15'
  UNION ALL
  SELECT 'Oscar Lopez','Community Manager','Marketing',1300.0,'2024-06-09'
  UNION ALL
  SELECT 'Patricia Flores','Reclutador','RRHH',1100.0,'2019-07-04'
  UNION ALL
  SELECT 'Roberto Castro','Auditor','Finanzas',900.0,'2025-09-04'
  UNION ALL
  SELECT 'Fernando Rojas','Ejecutivo de Ventas','Ventas',1950.0,'2021-12-03'
  UNION ALL
  SELECT 'Patricia Rojas','Desarrollador','TI',1050.0,'2021-02-13'
  UNION ALL
  SELECT 'Gabriela Reyes','Auditor','Finanzas',1300.0,'2023-06-07'
  UNION ALL
  SELECT 'Isabel Pena','Logistica','Operaciones',1000.0,'2020-09-24'
  UNION ALL
  SELECT 'Paula Cruz','Reclutador','RRHH',1650.0,'2021-11-11'
  UNION ALL
  SELECT 'Veronica Ruiz','Desarrollador','TI',1800.0,'2024-05-03'
  UNION ALL
  SELECT 'Isabel Flores','Asistente RRHH','RRHH',1450.0,'2025-07-21'
  UNION ALL
  SELECT 'Carmen Gomez','Ejecutivo de Ventas','Ventas',1550.0,'2022-12-19'
  UNION ALL
  SELECT 'Laura Reyes','Vendedor','Ventas',1500.0,'2020-09-16'
  UNION ALL
  SELECT 'Raul Soto','Analista','TI',1250.0,'2020-11-14'
  UNION ALL
  SELECT 'Laura Cruz','Disenador','Marketing',2250.0,'2022-09-28'
  UNION ALL
  SELECT 'Ricardo Aguilar','Analista','TI',1650.0,'2023-02-10'
  UNION ALL
  SELECT 'Paula Lopez','Ejecutivo de Ventas','Ventas',1600.0,'2020-09-04'
  UNION ALL
  SELECT 'Veronica Silva','Logistica','Operaciones',2400.0,'2021-03-12'
  UNION ALL
  SELECT 'Patricia Medina','Asistente RRHH','RRHH',800.0,'2023-08-01'
  UNION ALL
  SELECT 'Veronica Rojas','Soporte Tecnico','TI',1550.0,'2018-04-19'
  UNION ALL
  SELECT 'Hector Herrera','Analista','TI',1000.0,'2020-03-22'
  UNION ALL
  SELECT 'Jose Castro','Vendedor','Ventas',2150.0,'2021-09-25'
  UNION ALL
  SELECT 'Sofia Rojas','Jefe de Operaciones','Operaciones',2050.0,'2023-08-17'
  UNION ALL
  SELECT 'Luis Vargas','Ejecutivo de Ventas','Ventas',1000.0,'2023-01-19'
  UNION ALL
  SELECT 'Daniela Vargas','Disenador','Marketing',800.0,'2019-12-21'
  UNION ALL
  SELECT 'Lucia Ruiz','Desarrollador','TI',1850.0,'2019-09-08'
  UNION ALL
  SELECT 'Andres Torres','Analista Financiero','Finanzas',1200.0,'2025-04-26'
  UNION ALL
  SELECT 'Sofia Soto','Supervisor','Ventas',1100.0,'2024-06-14'
  UNION ALL
  SELECT 'Raul Ruiz','Supervisor','Ventas',1100.0,'2018-07-24'
  UNION ALL
  SELECT 'Luis Torres','Contador','Finanzas',1400.0,'2025-03-14'
  UNION ALL
  SELECT 'Paula Vargas','Gerente RRHH','RRHH',1000.0,'2025-09-04'
  UNION ALL
  SELECT 'Lucia Vargas','Analista','TI',1300.0,'2024-08-16'
  UNION ALL
  SELECT 'Carlos Diaz','Gerente RRHH','RRHH',2000.0,'2018-07-09'
  UNION ALL
  SELECT 'Diego Aguilar','Supervisor','Ventas',2350.0,'2020-04-10'
  UNION ALL
  SELECT 'Daniela Aguilar','Reclutador','RRHH',950.0,'2023-01-02'
  UNION ALL
  SELECT 'Valentina Medina','Community Manager','Marketing',1300.0,'2018-09-03'
  UNION ALL
  SELECT 'Roberto Mora','Reclutador','RRHH',1550.0,'2024-02-19'
  UNION ALL
  SELECT 'Roberto Ruiz','Asistente RRHH','RRHH',1050.0,'2024-11-19'
  UNION ALL
  SELECT 'Elena Castro','Analista de Marketing','Marketing',1450.0,'2023-04-09'
  UNION ALL
  SELECT 'Ricardo Silva','Ejecutivo de Ventas','Ventas',1750.0,'2025-06-25'
  UNION ALL
  SELECT 'Paula Mendoza','Analista','TI',1100.0,'2019-09-07'
  UNION ALL
  SELECT 'Maria Reyes','Community Manager','Marketing',1000.0,'2021-06-10'
  UNION ALL
  SELECT 'Veronica Aguilar','Gerente RRHH','RRHH',1750.0,'2018-11-27'
  UNION ALL
  SELECT 'Ricardo Soto','Community Manager','Marketing',1200.0,'2022-02-04'
  UNION ALL
  SELECT 'Maria Castro','Jefe de Operaciones','Operaciones',1700.0,'2021-12-11'
  UNION ALL
  SELECT 'Gabriela Castro','Asistente RRHH','RRHH',2400.0,'2025-05-28'
  UNION ALL
  SELECT 'Gabriela Ortiz','Analista','TI',1650.0,'2018-01-11'
  UNION ALL
  SELECT 'Carmen Diaz','Asistente RRHH','RRHH',2200.0,'2024-09-01'
  UNION ALL
  SELECT 'Isabel Gomez','Analista','TI',900.0,'2023-10-18'
  UNION ALL
  SELECT 'Maria Ruiz','Gerente RRHH','RRHH',1750.0,'2023-01-12'
  UNION ALL
  SELECT 'Luis Pena','Asistente RRHH','RRHH',1100.0,'2023-09-28'
  UNION ALL
  SELECT 'Hector Gomez','Vendedor','Ventas',1550.0,'2020-03-14'
  UNION ALL
  SELECT 'Hector Flores','Desarrollador','TI',2100.0,'2021-05-06'
  UNION ALL
  SELECT 'Laura Ruiz','Coordinador','Operaciones',2300.0,'2021-04-27'
  UNION ALL
  SELECT 'Miguel Vargas','Supervisor','Ventas',1500.0,'2018-11-07'
  UNION ALL
  SELECT 'Carmen Mora','Supervisor','Ventas',1650.0,'2023-11-17'
  UNION ALL
  SELECT 'Veronica Aguilar','Vendedor','Ventas',1850.0,'2018-02-09'
  UNION ALL
  SELECT 'Carmen Ruiz','Asistente RRHH','RRHH',1100.0,'2024-06-24'
  UNION ALL
  SELECT 'Roberto Medina','Auditor','Finanzas',1150.0,'2024-10-07'
  UNION ALL
  SELECT 'Isabel Ortiz','Contador','Finanzas',800.0,'2021-06-14'
  UNION ALL
  SELECT 'Roberto Flores','Soporte Tecnico','TI',1150.0,'2022-09-10'
  UNION ALL
  SELECT 'Elena Cruz','Logistica','Operaciones',1700.0,'2020-04-14'
  UNION ALL
  SELECT 'Ricardo Diaz','Logistica','Operaciones',1750.0,'2024-09-27'
  UNION ALL
  SELECT 'Miguel Torres','Soporte Tecnico','TI',2150.0,'2023-08-15'
  UNION ALL
  SELECT 'Sofia Medina','Vendedor','Ventas',2300.0,'2020-11-03'
  UNION ALL
  SELECT 'Ricardo Silva','Analista Financiero','Finanzas',1850.0,'2019-04-22'
  UNION ALL
  SELECT 'Oscar Torres','Contador','Finanzas',1250.0,'2018-01-08'
  UNION ALL
  SELECT 'Raul Mora','Vendedor','Ventas',2250.0,'2024-11-19'
  UNION ALL
  SELECT 'Isabel Cruz','Asistente RRHH','RRHH',2350.0,'2024-04-05'
  UNION ALL
  SELECT 'Ana Soto','Jefe de Operaciones','Operaciones',2150.0,'2021-03-26'
  UNION ALL
  SELECT 'Paula Ruiz','Jefe de Operaciones','Operaciones',1550.0,'2019-08-05'
  UNION ALL
  SELECT 'Valentina Aguilar','Vendedor','Ventas',1800.0,'2025-10-27'
  UNION ALL
  SELECT 'Diego Aguilar','Jefe de Operaciones','Operaciones',2200.0,'2020-12-28'
  UNION ALL
  SELECT 'Carmen Vargas','Supervisor','Ventas',1650.0,'2025-11-08'
  UNION ALL
  SELECT 'Lucia Rojas','Auditor','Finanzas',1550.0,'2022-06-11'
  UNION ALL
  SELECT 'Maria Gomez','Disenador','Marketing',1500.0,'2024-12-05'
  UNION ALL
  SELECT 'Lucia Ortiz','Coordinador','Operaciones',2100.0,'2023-09-15'
  UNION ALL
  SELECT 'Sofia Ortiz','Ejecutivo de Ventas','Ventas',2000.0,'2018-10-13'
  UNION ALL
  SELECT 'Javier Rojas','Ejecutivo de Ventas','Ventas',2000.0,'2024-09-24'
  UNION ALL
  SELECT 'Oscar Mendoza','Jefe de Operaciones','Operaciones',1500.0,'2025-04-09'
  UNION ALL
  SELECT 'Ana Cruz','Supervisor','Ventas',1850.0,'2024-12-06'
  UNION ALL
  SELECT 'Roberto Aguilar','Ejecutivo de Ventas','Ventas',850.0,'2024-10-19'
  UNION ALL
  SELECT 'Lucia Silva','Coordinador','Operaciones',2150.0,'2020-08-06'
) AS nuevos
WHERE (SELECT COUNT(*) FROM empleados) = 0;

INSERT INTO asistencias (empleado_id, fecha, horas_trabajadas, horas_extra)
SELECT e.id, CURRENT_DATE(), 8, FLOOR(RAND()*4)
FROM empleados e
WHERE (SELECT COUNT(*) FROM asistencias) = 0;

INSERT INTO solicitudes_vacaciones (empleado_id, fecha_inicio, fecha_fin, estado)
SELECT e.id, DATE_ADD(CURRENT_DATE(), INTERVAL 15 DAY), DATE_ADD(CURRENT_DATE(), INTERVAL 22 DAY), 'Pendiente'
FROM empleados e
WHERE e.id % 7 = 0 AND (SELECT COUNT(*) FROM solicitudes_vacaciones) = 0;
