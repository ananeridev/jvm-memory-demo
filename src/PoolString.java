import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PoolString {

    // Teste de Permanet Generation com String - POOL -

    public static final int MEGA = 1024 * 1024;
    public static final String FORMAT = "%.2f mb";

    public static void main (String args []) {

        String str = "Oiee Marte";

        List textos = new ArrayList();
        MemoryPoolMXBean mp = getPermGenMemory();

        for (int i = 0; true; i++){
            String str2 = (str + i).intern();

            if (i % 100000 == 0) {
                MemoryUsage mu = mp.getUsage();
                System.out.println("PermGen inicial: " +
                        String.format(FORMAT, (double) mu.getInit() / MEGA) +
                        "commitada: " + String.format(FORMAT, (double) mu.getCommitted() / MEGA) +
                        "utilizada: " + String.format(FORMAT, (double) mu.getUsed() / MEGA) +
                        "Max: " + String.format(FORMAT, (double) mu.getMax() / MEGA));
            }

            if ( i == 200000){
                System.out.println("Retirar a referencia das Strings do Pool" );
                textos = new ArrayList();
            }

            textos.add(str2);
        }
    }

    public static MemoryPoolMXBean getPermGenMemory(){
        MemoryPoolMXBean mp = null;
        List<MemoryPoolMXBean> lista = ManagementFactory.getMemoryPoolMXBeans();

        for (MemoryPoolMXBean m : lista){
                if ((m.getType() == MemoryType.NON_HEAP)
                        && m.getName().toUpperCase().indexOf("METASPACE") != -1){
                    mp = m;
                    break;
                }
        }
        return mp;
    }

}
