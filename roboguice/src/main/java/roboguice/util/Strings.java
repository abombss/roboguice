package roboguice.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Strings {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * Like join, but allows for a distinct final delimiter.  For english sentences such
     * as "Alice, Bob and Charlie" use ", " and " and " as the delimiters.
     *
     * @param delimiter usually ", "
     * @param lastDelimiter usually " and "
     * @param objs the objects
     * @param <T> the type
     * @return a string
     */
    public static <T> String joinAnd( final String delimiter, final String lastDelimiter, final Collection<T> objs ) {
        if (objs == null || objs.isEmpty())
          return "";

        final Iterator<T> iter = objs.iterator();
        final StringBuffer buffer = new StringBuffer(Strings.toString(iter.next()));
        int i=1;
        while (iter.hasNext()) {
            final T obj = iter.next();
            if(notEmpty(obj)) buffer.append( ++i == objs.size() ? lastDelimiter : delimiter).append(Strings.toString(obj));
        }
        return buffer.toString();
    }

    public static <T> String joinAnd( final String delimiter, final String lastDelimiter, final T... objs ) {
        return joinAnd(delimiter, lastDelimiter, Arrays.asList(objs));
    }

    public static <T> String join( final String delimiter, final Collection<T> objs) {
      if (objs == null || objs.isEmpty())
        return "";

      final Iterator<T> iter = objs.iterator();
      final StringBuffer buffer = new StringBuffer(Strings.toString(iter.next()));

      while (iter.hasNext()) {
          final T obj = iter.next();
          if(notEmpty(obj)) buffer.append(delimiter).append(Strings.toString(obj));
      }
      return buffer.toString();
    }

    public static <T> String join(final String delimiter, final T... objects ) {
        return join(delimiter, Arrays.asList(objects));
    }

    public static String toString(InputStream input) {
        StringWriter sw = new StringWriter();
        copy( new InputStreamReader(input), sw);
        return sw.toString();
    }

    public static String toString(Reader input) {
        StringWriter sw = new StringWriter();
        copy(input, sw);
        return sw.toString();
    }

    public static int copy(Reader input, Writer output) {
        long count = copyLarge(input, output);
        return count > Integer.MAX_VALUE ? -1 : (int)count;
    }

    public static long copyLarge(Reader input, Writer output) throws RuntimeException {
        try {
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            long count = 0;
            int n;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
                count += n;
            }
            return count;
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public static String toString( final Object o ) {
        return toString(o,"");
    }

    public static String toString( final Object o, final String def ) {
        return o==null ? def :
                o instanceof InputStream ? toString((InputStream)o) :
                o instanceof Reader ? toString((Reader)o) :
                o instanceof Object[] ? Strings.join(", ",(Object[])o) :
                o instanceof Collection ? Strings.join(", ", (Collection<?>)o) : o.toString();
    }

    public static boolean isEmpty( final Object o ) {
        return toString(o).trim().length()==0;
    }

    public static boolean notEmpty( final Object o ) {
        return toString(o).trim().length()!=0;
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            final MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            final byte messageDigest[] = digest.digest();

            // Create Hex String
            final StringBuffer hexString = new StringBuffer();
            for (byte aMessageDigest : messageDigest)
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String capitalize( String s ) {
        final String c = Strings.toString(s);
        return c.length()>=2 ? c.substring(0,1).toUpperCase() + c.substring(1) : c.length()>=1 ? c.toUpperCase() : c; 
    }

    public static boolean equals( Object a, Object b ) {
        return Strings.toString(a).equals(Strings.toString(b));
    }

    public static boolean equalsIgnoreCase( Object a, Object b ) {
        return Strings.toString(a).toLowerCase().equals(Strings.toString(b).toLowerCase());
    }

    public static String[] chunk( String str, int chunkSize ) {
        if( isEmpty(str) || chunkSize==0 )
            return new String[0];

        final int len = str.length();
        final int arrayLen = ((len-1)/chunkSize)+1;
        final String[] array = new String[arrayLen];
        for( int i=0; i<arrayLen; ++i )
            array[i] = str.substring(i*chunkSize,(i*chunkSize)+chunkSize<len ? (i*chunkSize)+chunkSize : len);

        return array;
    }

}
