
package PaymentService;



import java.util.List;

/**
 *
 * @author wejde
 * @param <T>
 */
public interface service <T>{
    void insert(T object );
    void delete(T object);
    void update(T object);
   List<T> findAll();
    
}

