
import NEU.ET39.MAT200.BaconCipher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaconCipherTest {

    @Test
    public void testEncode() {
        BaconCipher bc = new BaconCipher();
        String message = "dId sOmEbody SaY baCoN? thERe’s nOThInG MoRe DEliciOus.";
        String expected = "010010100001010010100110001101011010110000100";
        assertEquals(expected, bc.encrypt(message));
    }

    @Test
    public void testDecode() {
        BaconCipher bc = new BaconCipher();
        String encodedMessage = "010010100001010010100110001101011010110000100";
        String expected = "K I/J L L N O O N E ";
        assertEquals(expected, bc.decrypt(encodedMessage));
    }

    @Test
    public void testEncodeEmptyMessage() {
        BaconCipher bc = new BaconCipher();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bc.encrypt("");
        });
        assertEquals("Message cannot be empty", exception.getMessage());
    }

    @Test
    public void testDecodeEmptyMessage() {
        BaconCipher bc = new BaconCipher();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bc.decrypt("");
        });
        assertEquals("Encoded message cannot be empty", exception.getMessage());
    }
}