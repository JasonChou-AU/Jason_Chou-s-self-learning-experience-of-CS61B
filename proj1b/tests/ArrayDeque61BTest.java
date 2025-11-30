import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void TestForAll(){
        ArrayDeque61B<Integer> adq = new ArrayDeque61B<>();
        assertThat(adq.isEmpty()).isEqualTo(true);
        adq.addFirst(1);
        assertThat(adq.isEmpty()).isEqualTo(false);
        assertThat(adq.toList()).isEqualTo(List.of(1));
        adq.addLast(2);
        assertThat(adq.toList()).isEqualTo(List.of(1, 2));
        for (int i = 3; i <= 8; i++)
            adq.addLast(i);
        assertThat(adq.toList()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8));
        adq.addLast(10);
        assertThat(adq.size()).isEqualTo(9);
        assertThat(adq.toList()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 10));
        int temp = adq.removeFirst();
        assertThat(temp).isEqualTo(1);
        assertThat(adq.size()).isEqualTo(8);
        temp = adq.removeLast();
        assertThat(temp).isEqualTo(10);
        assertThat(adq.size()).isEqualTo(7);
        assertThat(adq.get(2)).isEqualTo(4);
        assertThat(adq.get(10)).isEqualTo(null);
        assertThat(adq.getRecursive(4)).isEqualTo(6);
    }

}
