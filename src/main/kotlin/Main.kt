/**
 * TODO: Extras:
 * No activar portal fins 3 pares noels
 * Afegir Puntuació
 * Guardar Puntuació amb nom d'usuari
 */

fun main() {
    var sortirAplicacio: Boolean = false

    mostrarMenuDificultat()

    // Declaració de variables necessaries per pasar a les funcions
    val DIFICULTAT: Int = escollirDificultat()
    val TAMANY: Int = DIFICULTAT * 10 // La dificultat es 1, 2 o 3 el tamany del taulell es la dificultat per 10
    // Poso el robot abaix a l'esquerra del taulell i li dono les vides segons la dificultat escollida
    val ROBOT = Robot()
    ROBOT.posicioRobot[0] = 0
    ROBOT.posicioRobot[1] = TAMANY - 1
    ROBOT.vides = DIFICULTAT + 1
    val TAULELL = jocTaulell(TAMANY, ROBOT) // Crea el taulell del joc del tamany escollit

    mostrarTaulell(TAMANY, TAMANY, TAULELL, ROBOT)

    // Comença el joc
    menuInstruccions()
    do {
        val INSTRUCCIOUSUARI = validarInstruccio()
        sortirAplicacio = executarInstruccio(INSTRUCCIOUSUARI, TAMANY, TAULELL, ROBOT)
    } while (!sortirAplicacio && ROBOT.vides > 0)
    if (ROBOT.vides == 0) println(RED_BOLD + "GAME OVER" + RESET)
    else println("Espero que t'hagi agradat l'aplicació")
}