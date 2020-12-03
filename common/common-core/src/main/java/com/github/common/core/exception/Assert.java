package com.github.common.core.exception;

import com.github.common.core.ServerResponseEnum;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
/**
 * @author peach
 * @since 2020/12/2 15:09
 */
public abstract class Assert {
    public Assert() {
    }

    public static void isTrue(boolean expression, String message) {
        isTrue(expression,()-> message);
    }

    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new CommonException(nullSafeGet(messageSupplier)).showMore();
        }
    }

    public static <T extends Number> void less(Comparable<T> object, T target, ServerResponseEnum serverResponseEnum) {
        if (object.compareTo(target) > 0) {
            throw new CommonException(serverResponseEnum).showMore();
        }
    }

    public static void isNull(Object object, String message) {
        isNull(object, ()-> message);
    }

    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new CommonException(nullSafeGet(messageSupplier)).showMore();
        }
    }

    public static void notNull(Object object, String message) {
        notNull(object, ()-> message);
    }

    public static void notNull(Object object, ServerResponseEnum serverResponseEnum) {
        if (object == null) {
            throw new CommonException(serverResponseEnum).showMore();
        }
    }

    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new CommonException(nullSafeGet(messageSupplier)).showMore();
        }
    }

    public static void notEmpty(Object[] array, String message) {
        notEmpty(array,() -> message);
    }

    public static void notEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (array == null || array.length == 0) {
            throw new CommonException(nullSafeGet(messageSupplier)).showMore();
        }
    }

    public static void noNullElements(Object[] array, String message) {
        noNullElements(array, () -> message);
    }

    public static void noNullElements(Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new CommonException(nullSafeGet(messageSupplier)).showMore();
                }
            }
        }

    }

    public static void notEmpty(Collection<?> collection, String message) {
        notEmpty(collection, () -> message);
    }

    public static void notEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection == null || collection.size() == 0) {
            throw new CommonException(nullSafeGet(messageSupplier)).showMore();
        }
    }


    public static void noNullElements(Collection<?> collection, String message) {
        noNullElements(collection, ()-> message);
    }

    public static void noNullElements(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new CommonException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        notEmpty(map, () -> message);
    }

    public static void notEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (map == null || map.size() == 0) {
            throw new CommonException(nullSafeGet(messageSupplier));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        isInstanceOf(type,obj,() -> message);
    }

    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, nullSafeGet(messageSupplier));
        }

    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        isInstanceOf(type, obj, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        isAssignable(superType, subType, () -> message);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, nullSafeGet(messageSupplier));
        }

    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = obj != null ? obj.getClass().getName() : "null";
        String result = "";
        boolean defaultMessage = true;
        if (msg != null && msg.length() != 0) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }

        if (defaultMessage) {
            result = result + "Object of class [" + className + "] must be an instance of " + type;
        }

        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (msg != null && msg.length() != 0) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }

        if (defaultMessage) {
            result = result + subType + " is not assignable to " + superType;
        }

        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? messageSupplier.get() : null;
    }
}