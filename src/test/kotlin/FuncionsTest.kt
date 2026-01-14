import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FuncionsTest {
    @Test
    fun checkIfAccelerarAumentaVelocitatSiEsMenorQue5() {
        val EXPECTED =  2
        val ROBOT = Robot(velocitat = 1)
        accelerar(ROBOT)

        assertEquals(EXPECTED, ROBOT.velocitat)
        assertFalse(ROBOT.velocitat == 1)
    }
    @Test
    fun checkIfAccelerarNoAumentaVelocitatSiEsMajorQue5() {
        val EXPECTED = 5
        val ROBOT = Robot(velocitat = 5)
        accelerar(ROBOT)
        assertEquals(EXPECTED, ROBOT.velocitat)
        assertFalse(ROBOT.velocitat == 6)
    }

    @Test
    fun checkIfFrenarRedueixVelocitatSiEsMajorQue0() {
        val EXPECTED =  0
        val ROBOT = Robot(velocitat = 1)
        frenar(ROBOT)

        assertEquals(EXPECTED, ROBOT.velocitat)
        assertFalse(ROBOT.velocitat == 1)
    }
    @Test
    fun checkIfFrenarNoRedueixVelocitatSiEsMenorQue0() {
        val EXPECTED = 0
        val ROBOT = Robot(velocitat = 0)
        frenar(ROBOT)
        assertEquals(EXPECTED, ROBOT.velocitat)
        assertFalse(ROBOT.velocitat == -1)
    }

    @Test
    fun checkIfReiniciarPosicioRetornaAlRobotAlIniciDelTaulell() {
        val TAMANY = 10
        val ROBOT = Robot()
        ROBOT.velocitat = 2
        ROBOT.posicioRobot[0] = 5
        ROBOT.posicioRobot[1] = 7
        reiniciarPosicio(TAMANY, ROBOT)

        assertEquals(0, ROBOT.posicioRobot[0])
        assertEquals(TAMANY - 1, ROBOT.posicioRobot[1])
        assertEquals(1, ROBOT.velocitat)
        assertFalse(8 == ROBOT.velocitat)
    }

    @Test
    fun checkIfRobotAvancaCorrectamentSenseChocar() {
        val TAMANY = 10
        val ROBOT = Robot()
        ROBOT.posicioRobot[0] = 0
        ROBOT.posicioRobot[1] = TAMANY - 1
        ROBOT.vides = 2
        val TAULELL = Array(TAMANY) { Array(TAMANY) { Taulell.Totxanes } }
        val ARRIBAPORTAL = comprovacioMoviment(ROBOT, TAULELL,1,0) // Es mou cap a la dreta

        assertEquals(2, ROBOT.vides)
        assertEquals(1, ROBOT.posicioRobot[0])
        assertEquals(TAMANY - 1, ROBOT.posicioRobot[1])
        assertFalse(ARRIBAPORTAL)
    }
    @Test
    fun checkIfRobotSurtDelTaulellPerdVida() {
        val TAMANY = 10
        val TAULELL = Array(TAMANY) { Array(TAMANY) { Taulell.Totxanes } }
        val ROBOT = Robot()
        ROBOT.posicioRobot[0] = 0
        ROBOT.posicioRobot[1] = TAMANY - 1
        ROBOT.vides = 2

        val ARRIBAPORTAL = comprovacioMoviment(ROBOT, TAULELL,-1,0) // Es mou a l'esquerra (surt del taulell)

        assertEquals(1, ROBOT.vides)
        assertEquals(0, ROBOT.posicioRobot[0]) // No es mou
        assertFalse(ARRIBAPORTAL)
    }
    @Test
    fun checkIfRobotArribaPortalGuanyaNoPerdVida() {
        val TAMANY = 10
        val TAULELL = Array(TAMANY) { Array(TAMANY) { Taulell.Totxanes } }
        TAULELL[9][1] = Taulell.PortalMagic
        val ROBOT = Robot()
        ROBOT.posicioRobot[0] = 0
        ROBOT.posicioRobot[1] = TAMANY - 1
        ROBOT.vides = 2

        val ARRIBAPORTAL = comprovacioMoviment(ROBOT, TAULELL, 1, 0) // Es mou cap a la dreta (on esta el portal)

        assertEquals(2, ROBOT.vides)
        assertEquals(1, ROBOT.posicioRobot[0])
        assertEquals(9, ROBOT.posicioRobot[1])
        assertTrue(ARRIBAPORTAL)
    }
}