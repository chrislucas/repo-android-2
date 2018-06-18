package slidingpanellayout.xplore.com.br.xploredevice.utils;

/**
 * Created by r028367 on 29/01/2018.
 */

public class ArrayUtils {

    /**
     * Cria um subarray a partir de um array cujos limites sao @start e @end
     * o indice @end nao eh incluido
     * */

    public static <T> void buildSubArray(T [] array, T[] copy, int start, int end) {
        if(start < 0)
            throw new IllegalArgumentException("Indice inicial não pode ser menor que 0");

        else if(end > array.length)
            throw new IllegalArgumentException("Indice final nao pode ser maior que o tamaho do array original");

        else if( start > end)
            throw new IllegalArgumentException("Indice inicial nao pode ser maior que o final ");

        for(int i=start, j=0; i<end; i++)
            copy[j++] = array[i];
    }

    public static <T> void buildSubArray(T [] array, T [] copy, int start) {
        buildSubArray(array, copy, start, array.length);
    }

}
