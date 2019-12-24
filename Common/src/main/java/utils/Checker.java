package utils;

import exceptions.UncheckedException;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 封装 检查类
 */
public class Checker {
    private Checker(){

    }

    /**
     * 判断为null
     * @param obj
     * @param troubleSupplier
     * @param <O>
     * @param <T>
     * @return
     */
    public static <O,T extends UncheckedException> O checkIsNull(O obj, Supplier<T> troubleSupplier){
        Objects.requireNonNull(troubleSupplier);
        if(null != obj){
            throw  troubleSupplier.get();
        }
        return null;
    }

    /**
     * 是否为null  否抛出异常
     * @param obj
     * @param troubleSupplier
     * @param <O>
     * @param <T>
     * @return
     */
    public static <O,T extends UncheckedException> O  checkNoNull(O obj,Supplier<T> troubleSupplier){
        Objects.requireNonNull(troubleSupplier);
        return Optional.ofNullable(obj).orElseThrow(troubleSupplier);
    }

    /**
     * 判断是否为true
     * @param expr
     * @param troubleSupplier
     * @param <T>
     */
    public static <T extends UncheckedException> void checkTrue(boolean expr,Supplier<T> troubleSupplier){
        Objects.requireNonNull(troubleSupplier);
        if(!expr){
            throw troubleSupplier.get();
        }
    }



    /**
     * 判断true false
     * @param expr
     * @param troubleSupplier
     * @param <T>
     */
    public static <T extends UncheckedException> void checkFalse(boolean expr, Supplier<T> troubleSupplier) {
        Objects.requireNonNull(troubleSupplier);
        if (expr) {
            throw troubleSupplier.get();
        }
    }

    /**
     * 集合内都不符合predicate  抛出异常
     * @param items
     * @param predicate
     * @param troubleSupplier
     * @param <T>
     * @param <E>
     */
    public static <T extends UncheckedException, E> void checkEachState(
            List<E> items, Predicate<? super E> predicate, Supplier<T> troubleSupplier) {
        Objects.requireNonNull(items);
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(troubleSupplier);
        if (items.stream().noneMatch(predicate)) {
            throw troubleSupplier.get();
        }
    }

    /**
     * map检查
     * @param iterable
     * @param troubleSupplier
     * @param <O>
     * @param <T>
     * @return
     */
    public static <O extends Iterable<?>, T extends UncheckedException> O checkNonEmpty(O iterable, Supplier<T> troubleSupplier) {
        Objects.requireNonNull(troubleSupplier);
        checkTrue(Optional.ofNullable(iterable).map(Iterable::iterator).map(Iterator::hasNext).orElse(false), troubleSupplier);
        return iterable;
    }
}
