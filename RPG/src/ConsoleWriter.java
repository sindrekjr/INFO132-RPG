
/**
 * Klasse som tar seg av skriving av tekst til terminalvindu. 
 * 
 * @author skj006
 */
public class ConsoleWriter implements Output {
    public ConsoleWriter() {
        
    }
    
    public void write(String text) {
        System.out.print(text);
    }
}
