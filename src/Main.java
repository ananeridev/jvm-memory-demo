import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Locale;

import static java.lang.System.out;


public class Main {

    public static int MEGA = 1024 * 1024;
    public static String FORMAT = " (%.2fmb)";

    public static void main(String args[]){

        //o intellij ja traz os importas automaticamente referente a memória
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        //Aqui há uma identificação de memória com resposta HEAP
        out.println(":::::Memória heap:::::");
        out.println();
        MemoryUsage heapMemory = memoryMXBean.getHeapMemoryUsage();

        out.println("Tamanho Inicial :: " + heapMemory.getInit() +
                String.format(Locale.US, FORMAT,(double) heapMemory.getInit() / MEGA));
        out.println("Tamanho atual :: " +
                heapMemory.getInit() +
                String.format(Locale.US, FORMAT, (double) heapMemory.getCommitted() / MEGA));
        out.println("Usado :: " +heapMemory.getUsed() +
                String.format(Locale.US, FORMAT, (double) heapMemory.getUsed() / MEGA));
        out.println("Máximo :: " + heapMemory.getMax() +
                String.format(Locale.US, FORMAT, (double) heapMemory.getMax() / MEGA));
        out.println();

        //AGORA VAMOS FAZER PRATICAMENTE A MESMA COISA PORÉM COM A MEMÓRIA NON-HEAP
        out.println(":::Memória non-heap:::");
        out.println();
        MemoryUsage nonheapMemory = memoryMXBean.getNonHeapMemoryUsage();

        out.println("Tamanho inicial ::" + nonheapMemory.getInit() +
                String.format(Locale.US, FORMAT, (double) nonheapMemory.getInit() / MEGA));
        out.println("Tamanho Atual ::" + nonheapMemory.getCommitted() +
                String.format(Locale.US, FORMAT, (double) nonheapMemory.getCommitted() / MEGA));
        out.println("Usado ::" + nonheapMemory.getUsed() +
                String.format(Locale.US, FORMAT, (double) nonheapMemory.getUsed() / MEGA));
        out.println("Máximo ::" + nonheapMemory.getMax() +
                String.format(Locale.US, FORMAT, (double) nonheapMemory.getMax() / MEGA));
        out.println();


        // Funcionamento do pool de memórias na JVM
        out.println(":: Pool de memórias Organ. JVM ::");
        out.println();
        List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();

        for (MemoryPoolMXBean m :list) {

            out.println("Nome do Pool :: " + m.getName());
            out.println("Grupo ::" + m.getType());
            out.println();

            MemoryUsage usage = m.getUsage();
            out.println("Inicial: " + usage.getInit() +
                    String.format(Locale.US, FORMAT, (double) usage.getInit() / MEGA));
            out.println("Atual: " +usage.getCommitted() +
                    String.format(Locale.US, FORMAT, (double) usage.getCommitted() / MEGA));
            out.println("Usado: " + usage.getUsed() +
                    String.format(Locale.US, FORMAT, (double) usage.getUsed() / MEGA));
            out.println("Máximo: " + usage.getMax() +
                    String.format(Locale.US, FORMAT, (double) usage.getMax() / MEGA));
            out.println();
        }
    }
}
