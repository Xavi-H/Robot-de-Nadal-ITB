// ==== DIFICULTAT ====

/**
 * Mostra el menu per escollir la dificultat del joc amb colors.
 * @author Xavi-H
 * @since 18/12/2025
 */
fun mostrarMenuDificultat() {
    println("$CYAN_BOLD=== MENU DIFICULTAT ===")
    println("#$PURPLE_BOLD 1.$RESET $CYAN_BOLD FACIL -> PETIT  #")
    println("#$PURPLE_BOLD 2.$RESET $CYAN_BOLD NORMAL -> MITJA #")
    println("#$PURPLE_BOLD 3.$RESET $CYAN_BOLD DIFICIL -> GRAN #")
}
/**
 * Valida un valor correcte per la dificultat amb colors i missatges per parametres.
 * @param missatgeError Si introdueix un tipus de dada incorrecte.
 * @param missatgeWarning Si introduiex un numero fora del rang acceptat.
 * @param min Valor mínim acceptat.
 * @param max Valor màxim acceptat.
 * @author Xavi-H
 * @since 18/12/2025
 */
fun escollirDificultat(missatgeError: String = "Error! Valor introduit incorrecte.", missatgeWarning: String = "Warning! Escull un rang valid.", min: Int = 1, max: Int = 3): Int {
    var opcioValida: Boolean = false
    var opcioDificultat: Int?
    do {
        print("Escull una opció del menú: ")
        opcioDificultat = readln().toIntOrNull()
        if (opcioDificultat == null){
            println(RED + missatgeError + RESET)
        } else if ((opcioDificultat < min) || (opcioDificultat > max)) {
            println(YELLOW_BOLD + missatgeWarning + RESET)
        } else opcioValida = true
    } while (!opcioValida)
    return opcioDificultat?.toInt() ?: 0 // Converteix opcioDificultat en un int, si fos un altre cosa es converteix a 0, pero el control d'errors ja valida que sigui un numero del 1 al 3. Ho faig perque si no dona error.
}

// ==== CREACIÓ DEL TAULELL ====

/**
 * Crea el taulell del tamany escollit per l'usuari y crida a les funcions que coloquen les caselles
 * @author Xavi-H
 * @since 19/12/2025
 */
fun jocTaulell(tamany: Int, robot: Robot): Array<Array<Taulell>> {
    val TAULELL: Array<Array<Taulell>> = Array (tamany) { Array(tamany) {Taulell.Totxanes} }

    // Coloco els emojis al taulell
    colocarCaselles(tamany * 2, tamany, robot, TAULELL, Taulell.Roques)
    colocarCaselles(3, tamany, robot, TAULELL, Taulell.PareNoel)
    colocarCaselles(1, tamany, robot, TAULELL, Taulell.PortalMagic)

    return TAULELL
}
/**
 * Coloca les caselles al taulell de forma aleatoria
 * @param numeroCaselles Rep la cuantitat de caselles a colocar
 * @param tamany Rep el tamany del taulell per poder randomitzar correctament
 * @param robot Per no colocar res on esta el robot
 * @param taulell Per colocar les caselles al taulell
 * @param casella Rep el Enum class Taulell per poder dibuixar els emojis rebuts
 * @author Xavi-H
 * @since 3/1/2026
 */
fun colocarCaselles(numeroCaselles: Int, tamany: Int, robot: Robot, taulell: Array<Array<Taulell>>, casella: Taulell) {
    var casellesColocades: Int = 0
    while (casellesColocades < numeroCaselles) {
        val FILARANDOM = (0 until tamany).random()
        val COLUMNARANDOM = (0 until tamany).random()
        val CASELLAESROBOT = (robot.posicioRobot[0] == COLUMNARANDOM) && (robot.posicioRobot[1] == FILARANDOM)
        val CASELLAPEDRA = taulell[FILARANDOM][COLUMNARANDOM] == Taulell.Roques
        val CASELLAPARENOEL = taulell[FILARANDOM][COLUMNARANDOM] == Taulell.PareNoel
        if (!CASELLAESROBOT && !CASELLAPEDRA && !CASELLAPARENOEL) {
            taulell[FILARANDOM][COLUMNARANDOM] = casella
            casellesColocades++
        }
    }
}

// ==== MOSTRAR I VALIDAR INSTRUCCIONS ====

/**
 * Mostra el menu d'opcions que el robot pot entendre
 * @author Xavi-H
 * @since 19/12/2025
 */
fun menuInstruccions(){
    println(CYAN_BOLD + """
        ==== MENU D'OPCIONS ====
        DALT
        BAIX
        DRETA
        ESQUERRA
        ACCELERAR
        FRENAR
        POSICIO
        MOSTRAR
        VELOCITAT
        REINICIAR
        END
    """.trimIndent() + RESET)
}
/**
 * Valida l'entrada de la instrucció feta per l'usuari.
 * Detecta si es un numero, es null o es un espai i mostra un missatge segons que sigui.
 * @param missatgeError Si no introdueix un string.
 * @param missatgeWarning Si no escriu correctament una de les instruccions.
 * @author Xavi-H
 * @since 19/12/2025
 */
fun validarInstruccio(missatgeError: String = "Error! Valor introduit incorrecte.", missatgeWarning: String = "Warning! Escull una instrucció valida."): String? {
    var opcioValida: Boolean = false
    var instruccio: String?
    do {
        print(PURPLE_BOLD + "Escull una opció del menú: " + RESET)
        instruccio = readlnOrNull()
        if (instruccio.isNullOrBlank()/* Valida si es null o un espai */ || instruccio.all { it.isDigit() } /* Valida si es un numero */ ){
            println(RED + missatgeError + RESET)
        } else {
            when (instruccio) {
                "DALT" -> {opcioValida = true}
                "BAIX" -> {opcioValida = true}
                "DRETA" -> {opcioValida = true}
                "ESQUERRA" -> {opcioValida = true}
                "ACCELERAR" -> {opcioValida = true}
                "FRENAR" -> {opcioValida = true}
                "POSICIO" -> {opcioValida = true}
                "MOSTRAR" -> {opcioValida = true}
                "VELOCITAT" -> {opcioValida = true}
                "REINICIAR" -> {opcioValida = true}
                "END" -> {opcioValida = true}
                else -> {println(YELLOW_BOLD + missatgeWarning  + RESET)}
            }
        }
    } while (!opcioValida)
    return instruccio
}

// ==== EXECUTAR INSTRUCCIONS ====

/**
 * Crida a les funcions que executen les instruccions que enten el robot
 * @param instruccio rep la instruccio validada que ha introduit l'usuari
 * @param tamany rep el tamany del taulell per mostrar-ho correctament
 * @param taulell rep el taulell amb les seves caselles per mostrar-ho correctament
 * @param robot rep el robot per poder modificar els seus atributs (velocitat, posició i vides)
 */
fun executarInstruccio(instruccio: String?, tamany: Int, taulell: Array<Array<Taulell>>, robot: Robot): Boolean{
    var sortirAplicacio: Boolean = false
    when (instruccio) {
        "DALT"      -> { sortirAplicacio = comprovacioMoviment(robot, taulell, 0, -1)}
        "BAIX"      -> { sortirAplicacio = comprovacioMoviment(robot, taulell, 0, 1) }
        "DRETA"     -> { sortirAplicacio = comprovacioMoviment(robot, taulell, 1, 0) }
        "ESQUERRA"  -> { sortirAplicacio = comprovacioMoviment(robot, taulell, -1, 0)}
        "ACCELERAR" -> { accelerar(robot) }
        "FRENAR"    -> { frenar(robot) }
        "POSICIO"   -> { mostrarPosicio(robot, tamany) }
        "MOSTRAR"   -> { mostrarTaulell(tamany, tamany, taulell, robot) }
        "VELOCITAT" -> { mostrarVelocitat(robot) }
        "REINICIAR" -> { reiniciarPosicio(tamany, robot) }
        "END"       -> { sortirAplicacio = true }
    }
    return sortirAplicacio
}
/**
 * Aumenta la velocitat fins a un màxim de 5
 * @param robot Rep el robot per poder modificar la velocitat
 * @author Xavi-H
 * @since 30/12/2025
 */
fun accelerar(robot: Robot){
    if (robot.velocitat < 5) robot.velocitat++
    else println("El robot no pot anar més ràpid!!")
}
/**
 * Redueix la velocitat fins a un mínim de 0
 * @param robot Rep el robot per poder modificar la velocitat
 * @author Xavi-H
 * @since 30/12/2025
 */
fun frenar(robot: Robot){
    if (robot.velocitat > 0) robot.velocitat--
    else println("El robot no es mou, no pot anar més lent!!")
}
/**
 * Mostra el taulell
 * @param longitud Es el tamany que te el taulell aumenta segons la dificultat.
 * @param altura Es el tamany que te el taulell aumenta segons la dificultat.
 * @param taulell Rep l'array de tipus Taulell per poder imprimir-ho.
 * @param robot Rep la posició del robot y dibuixa la cara del robot en el taulell
 * @author Xavi-H
 * @since 19/12/2025
 */
fun mostrarTaulell(longitud: Int, altura: Int, taulell: Array<Array<Taulell>>, robot: Robot) {
    for (files in 0 until longitud) {
        for (columnes in 0 until altura){
            if ((robot.posicioRobot[0] == columnes) && (robot.posicioRobot[1] == files)) { print(Taulell.Robot.casella)} // Dibuixa el robot en la casella en la que estigui
            else { print(taulell[files][columnes].casella) }
        }
        println("")
    }
}
/**
 * Mostra per pantalla la velocitat del robot
 * @param robot rep el data class robot per poder mostrar la seva velocitat
 * @author Xavi-H
 * @since 29/12/2025
 */
fun mostrarVelocitat(robot: Robot){
    println("La velocitat actual del robot es de: ${robot.velocitat} m/s")
}
/**
 * Mostra la posició del robot en el taulell
 * @param robot rep les coordenades del robot
 * @param tamany rep el tamany del taulell per restarli a la poscició del robot
 * @author Xavi-H
 * @since 29/12/2025
 */
fun mostrarPosicio(robot: Robot, tamany: Int){
    val X = robot.posicioRobot[0]
    val Y = (robot.posicioRobot[1] - tamany + 1) * -1 // Li resto el tamany per mostrar coordenades mes atractives (x=0, y=0) y multiplico per -1 per pasar-ho a positiu i no complicar el calcul de la posicio al moure el robot
    println("La posició actual del robot es: ($X, $Y)")
}
/**
 * Reinicia la posicio i la velocitat del robot
 * @param tamany rep el tamany del taulell per saber on esta la cantonada
 * @param robot rep la classe robot per modificar el contingut
 * @author Xavi-H
 * @since 29/12/2025
 */
fun reiniciarPosicio(tamany: Int, robot: Robot){
    robot.posicioRobot[0] = 0
    robot.posicioRobot[1] = tamany - 1
    robot.velocitat = 1
}

// ==== COMPROVACIÓ MOVIMENT ====

/**
 * Valida si el moviment es valid o no, si surt del taulell, choca amb roca, pare noel, portal o dinamita.
 * Si el moviment es valid, canvia la posició del robot. Si no li resta una vida.
 * @param robot Rep la posició del robot
 * @param taulell Rep el taulell per saber en quina casella caura el robot
 * @param columna Es el desplaçament que fara en el eix X depenent si es cap a dalt, baix etc sera 0, -1 o 1
 * @param fila Es el desplaçament que fara en el eix Y depenent si es cap a dalt, baix etc sera 0, -1 o 1
 * @return Retorna si ha arribat al portal o no
 * @author Xavi-H
 * @since 1/1/2026
 */
fun comprovacioMoviment(robot: Robot, taulell: Array<Array<Taulell>>, columna: Int, fila: Int): Boolean{
    var sortitTaulell: Boolean = false
    var chocarRoca: Boolean = false
    var chocarDinamita: Boolean = false
    var chocarPortal: Boolean = false
    val MOVIMENTS = robot.velocitat
    var i = 0

    while (i < MOVIMENTS && !sortitTaulell && !chocarRoca && !chocarDinamita && !chocarPortal) {
        val COLUMNASEGUENT = robot.posicioRobot[0] + columna
        val FILASEGUENT    = robot.posicioRobot[1] + fila

        if ( FILASEGUENT < 0 || FILASEGUENT >= taulell.size || COLUMNASEGUENT < 0 || COLUMNASEGUENT >= taulell.size){ // Si surt del taulell
            robot.vides -= 1
            println("Has sortit del taulell. Has perdut una vida!")
            sortitTaulell = true
        } else if (taulell[FILASEGUENT][COLUMNASEGUENT] == Taulell.Roques) { // Si choca amb una roca
            robot.vides -= 1
            println("Has chocat amb una roca. Has perdut una vida!")
            chocarRoca = true
        } else if (taulell[FILASEGUENT][COLUMNASEGUENT] == Taulell.PareNoel) { // Si choca amb el pare noel
            robot.vides += 1
            println("Has guanyat una vida!!")
            if (taulell[robot.posicioRobot[1]][robot.posicioRobot[0]] != Taulell.Roques) taulell[robot.posicioRobot[1]][robot.posicioRobot[0]] = Taulell.Dinamita
            robot.posicioRobot[0] = COLUMNASEGUENT
            robot.posicioRobot[1] = FILASEGUENT
            taulell[FILASEGUENT][COLUMNASEGUENT] = Taulell.Roques
            i++
        } else if (taulell[FILASEGUENT][COLUMNASEGUENT] == Taulell.Dinamita) { // Si choca amb dinamita
            robot.vides -= 1
            println("Has chocat amb una dinamita. Has perdut una vida!")
            chocarDinamita = true
        } else if (taulell[FILASEGUENT][COLUMNASEGUENT] == Taulell.PortalMagic) { // Si choca amb el portal
            robot.posicioRobot[0] = COLUMNASEGUENT
            robot.posicioRobot[1] = FILASEGUENT
            println(GREEN_BOLD + "FELICITATS!!$RESET Has arribat al portal. Has guanyat el joc")
            chocarPortal = true
        } else { // No choca amb res, avança amb normalitat
            if (taulell[robot.posicioRobot[1]][robot.posicioRobot[0]] != Taulell.Roques) taulell[robot.posicioRobot[1]][robot.posicioRobot[0]] = Taulell.Dinamita
            robot.posicioRobot[0] = COLUMNASEGUENT
            robot.posicioRobot[1] = FILASEGUENT
            i++
        }
    }
    return chocarPortal
}
