/**
 * Conté la informació de la posició en el taulell, la velocitat i la vida del robot.
 * @author Xavi-H
 * @since 18/12/2025
 */
data class Robot (
    var posicioRobot: IntArray = IntArray(2),
    // Per aclarar la posició del robot:
    // posicioRobot[0] = x (horizontal / columnas)
    // posicioRobot[1] = y (vertical / filas)
    var velocitat: Int = 1,
    var vides: Int = 2
)