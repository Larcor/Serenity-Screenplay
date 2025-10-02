Feature: Open web page
  Yo como manuel
  quiero realizar una prueba de abrir una pagina
  Para comprobar el funcionamineto



  @prueba   #Con este escenario podemos verificar los dos primeros puntos solicitados por medio de la tabla Examples
Scenario Outline: Reservar cita con el calendario
   Given "<actor>" abre el navegador
   When Selecciona la fecha <year>-<month>-<day>
   Then En el campo aparece la fecha <year>-<month>-<day>


    Examples:
      | actor  | year | month | day |
      | Manuel | 2025 | 10    | 15  |
      | Manuel | 2026 | 02    | 05  |


  @prueba2
  Scenario Outline: Ingresar fecha en el campo
    Given "<actor>" abre el navegador
    When Ingresa la fecha <year>-<month>-<day> en el campo
    Then En el campo aparece la fecha <year>-<month>-<day>
    Examples:
      | actor  | year | month | day |
      | Manuel | 2024 | 12    | 22  |