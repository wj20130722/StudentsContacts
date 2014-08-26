package com.linkage.util;

import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

/**
 * <p>Operations on {@link java.lang.String} that are
 * <code>null</code> safe from org.apache.commons.lang.StringUtils.</p>
 *
 * <ul>
 *  <li><b>IsEmpty/IsBlank</b>
 *      - checks if a String contains text</li>
 *  <li><b>Equals</b>
 *      - compares two strings null-safe</li>
 *  <li><b>Remove</b>
 *      - removes part of a String</li>
 *  <li><b>Replace</b>
 *      - Searches a String and replaces one String with another</li>
 *  <li><b>DefaultString</b>
 *      - protects against a null input String</li>
 * </ul>
 *
 * <p>The <code>StringUtils</code> class defines certain words related to
 * String handling.</p>
 *
 * <ul>
 *  <li>null - <code>null</code></li>
 *  <li>empty - a zero-length string (<code>""</code>)</li>
 *  <li>space - the space character (<code>' '</code>, char 32)</li>
 *  <li>whitespace - the characters defined by {@link Character#isWhitespace(char)}</li>
 *  <li>trim - the characters &lt;= 32 as in {@link String#trim()}</li>
 * </ul>
 *
 * <p><code>StringUtils</code> handles <code>null</code> input Strings quietly.
 * That is to say that a <code>null</code> input will return <code>null</code>.
 * Where a <code>boolean</code> or <code>int</code> is being returned
 * details vary by method.</p>
 *
 * <p>A side effect of the <code>null</code> handling is that a
 * <code>NullPointerException</code> should be considered a bug in
 * <code>StringUtils</code> (except for deprecated methods).</p>
 *
 * <p>Methods in this class give sample code to explain their operation.
 * The symbol <code>*</code> is used to indicate any input including <code>null</code>.</p>
 *
 *
 * @author 杨永清





 * @version 1.0
 */
public  class StringUtils
{private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(StringUtils.class);
  protected static final Logger logger = Logger.getLogger(StringUtils.class);

  /**
   * 换行符




   */
  public static String LINE_SEPARATOR = System.getProperty("line.separator");

  /**
   * Html空格
   */
  public static final String HTML_BLANK = "&nbsp;";

  /**
   * The empty String <code>""</code>.
   * @since 2.0
   */
  public static final String EMPTY = "";

  /**
   * Represents a failed index search.
   * @since 2.1
   */
  public static final int INDEX_NOT_FOUND = -1;

  // Empty checks
  //-----------------------------------------------------------------------
  /**
   * <p>Checks if a String is empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * <p>NOTE: This method changed in Lang version 2.0.
   * It no longer trims the String.
   * That functionality is available in isBlank().</p>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is empty or null
   */
  public static boolean isEmpty(String str)
  {
    return str == null || str.length() == 0;
  }
  /**
   * 将字符串用某个字符填充成固定长度的字符串
   * @param src String             源字符串
   * @param filledChar char        填充字符
   * @param objLength int          填充后的字符串的长度
   * @return String
   */
  public static String fillCharToObjLenStr(String src,char filledChar,int objLength)
  {
    String result = src;
    for (int i=0;i<objLength-src.length();i++)
      result = filledChar+result;
    return result;
  }
  /**
   * 将字符串用某个字符在其字符串的后面填充成固定长度的字符串
   * @param src String
   * @param filledChar char
   * @param objLength int
   * @return String
   */
  public static String fillCharToObjLenAfterStr(String src,char filledChar,int objLength)
  {
    String result = src;
    for (int i=0;i<objLength-src.length();i++)
      result = result+filledChar;
    return result;
  }


  /**
   * <p>Checks if a String is not empty ("") and not null.</p>
   *
   * <pre>
   * StringUtils.isNotEmpty(null)      = false
   * StringUtils.isNotEmpty("")        = false
   * StringUtils.isNotEmpty(" ")       = true
   * StringUtils.isNotEmpty("bob")     = true
   * StringUtils.isNotEmpty("  bob  ") = true
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is not empty and not null
   */
  public static boolean isNotEmpty(String str)
  {
    return str != null && str.length() > 0;
  }

  /**
   * <p>Checks if a String is whitespace, empty ("") or null.</p>
   * <i>using org.apache.commons.lang.StringUtils.isBlank(String str)</i>
   * <pre>
   * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("bob")     = false
   * StringUtils.isBlank("  bob  ") = false
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is null, empty or whitespace
   * @since 2.0
   */
  public static boolean isBlank(String str)
  {
    return org.apache.commons.lang3.StringUtils.isBlank(str);
  }

  /**
   * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
   * <i>using org.apache.commons.lang.StringUtils.isNotBlank(String str)</i>
   * <pre>
   * StringUtils.isNotBlank(null)      = false
   * StringUtils.isNotBlank("")        = false
   * StringUtils.isNotBlank(" ")       = false
   * StringUtils.isNotBlank("bob")     = true
   * StringUtils.isNotBlank("  bob  ") = true
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is
   *  not empty and not null and not whitespace
   * @since 2.0
   */
  public static boolean isNotBlank(String str)
  {
    return org.apache.commons.lang3.StringUtils.isNotBlank(str);
  }

  /**
   * <p>Removes control characters (char &lt;= 32) from both
   * ends of this String, handling <code>null</code> by returning
   * <code>null</code>.</p>
   *
   * <p>The String is trimmed using {@link String#trim()}.
   * Trim removes start and end characters &lt;= 32.
   * To strip whitespace use {@link #strip(String)}.</p>
   *
   * <p>To trim your choice of characters, use the
   * {@link #strip(String, String)} methods.</p>
   *
   * <pre>
   * StringUtils.trim(null)          = null
   * StringUtils.trim("")            = ""
   * StringUtils.trim("     ")       = ""
   * StringUtils.trim("abc")         = "abc"
   * StringUtils.trim("    abc    ") = "abc"
   * </pre>
   *
   * @param str  the String to be trimmed, may be null
   * @return the trimmed string, <code>null</code> if null String input
   */
  public static String trim(String str)
  {
    return str == null ? null : str.trim();
  }

  /**
   * <p>Removes control characters (char &lt;= 32) from both
   * ends of this String returning <code>null</code> if the String is
   * empty ("") after the trim or if it is <code>null</code>.
   *
   * <p>The String is trimmed using {@link String#trim()}.
   * Trim removes start and end characters &lt;= 32.
   * To strip whitespace use {@link #stripToNull(String)}.</p>
   *
   * <pre>
   * StringUtils.trimToNull(null)          = null
   * StringUtils.trimToNull("")            = null
   * StringUtils.trimToNull("     ")       = null
   * StringUtils.trimToNull("abc")         = "abc"
   * StringUtils.trimToNull("    abc    ") = "abc"
   * </pre>
   *
   * @param str  the String to be trimmed, may be null
   * @return the trimmed String,
   *  <code>null</code> if only chars &lt;= 32, empty or null String input
   * @since 2.0
   */
  public static String trimToNull(String str)
  {
    String ts = trim(str);
    return isEmpty(ts) ? null : ts;
  }

  /**
   * <p>Removes control characters (char &lt;= 32) from both
   * ends of this String returning an empty String ("") if the String
   * is empty ("") after the trim or if it is <code>null</code>.
   *
   * <p>The String is trimmed using {@link String#trim()}.
   * Trim removes start and end characters &lt;= 32.
   * To strip whitespace use {@link #stripToEmpty(String)}.</p>
   *
   * <pre>
   * StringUtils.trimToEmpty(null)          = ""
   * StringUtils.trimToEmpty("")            = ""
   * StringUtils.trimToEmpty("     ")       = ""
   * StringUtils.trimToEmpty("abc")         = "abc"
   * StringUtils.trimToEmpty("    abc    ") = "abc"
   * </pre>
   *
   * @param str  the String to be trimmed, may be null
   * @return the trimmed String, or an empty String if <code>null</code> input
   * @since 2.0
   */
  public static String trimToEmpty(String str)
  {
    return str == null ? EMPTY : str.trim();
  }

  // Contains
  //-----------------------------------------------------------------------
  /**
   * <p>Checks if String contains a search String, handling <code>null</code>.
   * This method uses {@link String#indexOf(int)}.</p>
   *
   * <p>A <code>null</code> String will return <code>false</code>.</p>
   *
   * <pre>
   * StringUtils.contains(null, *)     = false
   * StringUtils.contains(*, null)     = false
   * StringUtils.contains("", "")      = true
   * StringUtils.contains("abc", "")   = true
   * StringUtils.contains("abc", "a")  = true
   * StringUtils.contains("abc", "z")  = false
   * </pre>
   *
   * @param str  the String to check, may be null
   * @param searchStr  the String to find, may be null
   * @return true if the String contains the search String,
   *  false if not or <code>null</code> string input
   * @since 2.0
   */
  public static boolean contains(String str, String searchStr)
  {
    if(str == null || searchStr == null)
    {
      return false;
    }
    return str.indexOf(searchStr) >= 0;
  }

  // Delete
  //-----------------------------------------------------------------------
  /**
   * <p>Deletes all 'space' characters from a String as defined by
   * {@link Character#isSpace(char)}.</p>
   *
   * <p>This is the only StringUtils method that uses the
   * <code>isSpace</code> definition. You are advised to use
   * {@link #deleteWhitespace(String)} instead as whitespace is much
   * better localized.</p>
   *
   * <pre>
   * StringUtils.deleteSpaces(null)           = null
   * StringUtils.deleteSpaces("")             = ""
   * StringUtils.deleteSpaces("abc")          = "abc"
   * StringUtils.deleteSpaces(" \t  abc \n ") = "abc"
   * StringUtils.deleteSpaces("ab  c")        = "abc"
   * StringUtils.deleteSpaces("a\nb\tc     ") = "abc"
   * </pre>
   *
   * <p>Spaces are defined as <code>{' ', '\t', '\r', '\n', '\b'}</code>
   * in line with the deprecated <code>isSpace</code> method.</p>
   *
   * @param str  the String to delete spaces from, may be null
   * @return the String without 'spaces', <code>null</code> if null String input
   * @deprecated Use the better localized {@link #deleteWhitespace(String)}.
   *             Method will be removed in Commons Lang 3.0.
   */
  public static String deleteSpaces(String str)
  {
    if(str == null)
    {
      return null;
    }
    return CharSetUtils.delete(str, " \t\r\n\b");
  }

  /**
   * <p>Deletes all whitespaces from a String as defined by
   * {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.deleteWhitespace(null)         = null
   * StringUtils.deleteWhitespace("")           = ""
   * StringUtils.deleteWhitespace("abc")        = "abc"
   * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
   * </pre>
   *
   * @param str  the String to delete whitespace from, may be null
   * @return the String without whitespaces, <code>null</code> if null String input
   */
  public static String deleteWhitespace(String str)
  {
    if(isEmpty(str))
    {
      return str;
    }
    int sz = str.length();
    char[] chs = new char[sz];
    int count = 0;
    for(int i = 0; i < sz; i++)
    {
      if(!Character.isWhitespace(str.charAt(i)))
      {
        chs[count++] = str.charAt(i);
      }
    }
    if(count == sz)
    {
      return str;
    }
    return new String(chs, 0, count);
  }
  /**
   * 为字符串的各个子字符串的头和尾追加字符串
   * @param str String              待处理的字符串
   * @param splitStr String         总字符串的分割字符串
   * @param needAddStr String       需要在子字符串上添加的字符串
   * @return String
   */
 /* public static String addCharToSubString(String str, String splitStr,String needAddStr)
  {
    String resultStr = "";

    String[] subStrArr = org.springframework.util.StringUtils.split(str,splitStr);
    for (int i=0;i<subStrArr.length;i++)
    {
      resultStr += needAddStr+subStrArr[i]+needAddStr;
      if (i!=subStrArr.length-1)
        resultStr += splitStr;
    }
    return resultStr;
  }*/
  /**
   * 将字符串分割成ArrayList
   * @param src String           待分割的字符串
   * @param splitStr String      分割字符串
   * @return ArrayList
   */
  /*public static ArrayList toArrayList(String src,String splitStr)
  {
    ArrayList list = new ArrayList();
    String[] ringIdArr = org.springframework.util.StringUtils.split(src,splitStr);
    for (int i=0;i<ringIdArr.length;i++)
      list.add(ringIdArr[i]);
    return list;
  }*/
  // Remove
  //-----------------------------------------------------------------------
  /**
   * <p>Removes a substring only if it is at the begining of a source string,
   * otherwise returns the source string.</p>
   *
   * <p>A <code>null</code> source string will return <code>null</code>.
   * An empty ("") source string will return the empty string.
   * A <code>null</code> search string will return the source string.</p>
   *
   * <pre>
   * StringUtils.removeStart(null, *)      = null
   * StringUtils.removeStart("", *)        = ""
   * StringUtils.removeStart(*, null)      = *
   * StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
   * StringUtils.removeStart("domain.com", "www.")       = "domain.com"
   * StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com"
   * StringUtils.removeStart("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  <code>null</code> if null String input
   * @since 2.1
   */
  public static String removeStart(String str, String remove)
  {
    if(isEmpty(str) || isEmpty(remove))
    {
      return str;
    }
    if(str.startsWith(remove))
    {
      return str.substring(remove.length());
    }
    return str;
  }

  /**
   * <p>Removes a substring only if it is at the end of a source string,
   * otherwise returns the source string.</p>
   *
   * <p>A <code>null</code> source string will return <code>null</code>.
   * An empty ("") source string will return the empty string.
   * A <code>null</code> search string will return the source string.</p>
   *
   * <pre>
   * StringUtils.removeEnd(null, *)      = null
   * StringUtils.removeEnd("", *)        = ""
   * StringUtils.removeEnd(*, null)      = *
   * StringUtils.removeEnd("www.domain.com", ".com.")  = "www,domain"
   * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
   * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
   * StringUtils.removeEnd("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  <code>null</code> if null String input
   * @since 2.1
   */
  public static String removeEnd(String str, String remove)
  {
    if(isEmpty(str) || isEmpty(remove))
    {
      return str;
    }
    if(str.endsWith(remove))
    {
      return str.substring(0, str.length() - remove.length());
    }
    return str;
  }

  /**
   * <p>Removes all occurances of a substring from within the source string.</p>
   *
   * <p>A <code>null</code> source string will return <code>null</code>.
   * An empty ("") source string will return the empty string.
   * A <code>null</code> remove string will return the source string.
   * An empty ("") remove string will return the source string.</p>
   *
   * <pre>
   * StringUtils.remove(null, *)        = null
   * StringUtils.remove("", *)          = ""
   * StringUtils.remove(*, null)        = *
   * StringUtils.remove(*, "")          = *
   * StringUtils.remove("queued", "ue") = "qd"
   * StringUtils.remove("queued", "zz") = "queued"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  <code>null</code> if null String input
   * @since 2.1
   */
  public static String remove(String str, String remove)
  {
    if(isEmpty(str) || isEmpty(remove))
    {
      return str;
    }
    return replace(str, remove, "", -1);
  }

  /**
   * <p>Removes all occurances of a character from within the source string.</p>
   *
   * <p>A <code>null</code> source string will return <code>null</code>.
   * An empty ("") source string will return the empty string.</p>
   *
   * <pre>
   * StringUtils.remove(null, *)       = null
   * StringUtils.remove("", *)         = ""
   * StringUtils.remove("queued", 'u') = "qeed"
   * StringUtils.remove("queued", 'z') = "queued"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the char to search for and remove, may be null
   * @return the substring with the char removed if found,
   *  <code>null</code> if null String input
   * @since 2.1
   */
  public static String remove(String str, char remove)
  {
    if(isEmpty(str) || str.indexOf(remove) == -1)
    {
      return str;
    }
    char[] chars = str.toCharArray();
    int pos = 0;
    for(int i = 0; i < chars.length; i++)
    {
      if(chars[i] != remove)
      {
        chars[pos++] = chars[i];
      }
    }
    return new String(chars, 0, pos);
  }

  // Replacing
  //-----------------------------------------------------------------------
  /**
   * <p>Replaces all occurrences of a String within another String.</p>
   *
   * <p>A <code>null</code> reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replace(null, *, *)        = null
   * StringUtils.replace("", *, *)          = ""
   * StringUtils.replace("any", null, *)    = "any"
   * StringUtils.replace("any", *, null)    = "any"
   * StringUtils.replace("any", "", *)      = "any"
   * StringUtils.replace("aba", "a", null)  = "aba"
   * StringUtils.replace("aba", "a", "")    = "b"
   * StringUtils.replace("aba", "a", "z")   = "zbz"
   * </pre>
   *
   * @see #replace(String text, String repl, String with, int max)
   * @param text  text to search and replace in, may be null
   * @param repl  the String to search for, may be null
   * @param with  the String to replace with, may be null
   * @return the text with any replacements processed,
   *  <code>null</code> if null String input
   */
  public static String replace(String text, String repl, String with)
  {
    return replace(text, repl, with, -1);
  }

  /**
   * <p>Replaces a String with another String inside a larger String,
   * for the first <code>max</code> values of the search String.</p>
   *
   * <p>A <code>null</code> reference passed to this method is a no-op.</p>
   *
   * <pre>
   * StringUtils.replace(null, *, *, *)         = null
   * StringUtils.replace("", *, *, *)           = ""
   * StringUtils.replace("any", null, *, *)     = "any"
   * StringUtils.replace("any", *, null, *)     = "any"
   * StringUtils.replace("any", "", *, *)       = "any"
   * StringUtils.replace("any", *, *, 0)        = "any"
   * StringUtils.replace("abaa", "a", null, -1) = "abaa"
   * StringUtils.replace("abaa", "a", "", -1)   = "b"
   * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
   * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
   * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
   * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
   * </pre>
   *
   * @param text  text to search and replace in, may be null
   * @param repl  the String to search for, may be null
   * @param with  the String to replace with, may be null
   * @param max  maximum number of values to replace, or <code>-1</code> if no maximum
   * @return the text with any replacements processed,
   *  <code>null</code> if null String input
   */
  public static String replace(String text, String repl, String with, int max)
  {
    if(text == null || isEmpty(repl) || with == null || max == 0)
    {
      return text;
    }

    StringBuffer buf = new StringBuffer(text.length());
    int start = 0, end = 0;
    while((end = text.indexOf(repl, start)) != -1)
    {
      buf.append(text.substring(start, end)).append(with);
      start = end + repl.length();

      if(--max == 0)
      {
        break;
      }
    }
    buf.append(text.substring(start));
    return buf.toString();
  }

  // Defaults
  //-----------------------------------------------------------------------
  /**
   * <p>Returns either the passed in String,
   * or if the String is <code>null</code>, an empty String ("").</p>
   *
   * <pre>
   * StringUtils.defaultString(null)  = ""
   * StringUtils.defaultString("")    = ""
   * StringUtils.defaultString("bat") = "bat"
   * </pre>
   *
   * @see ObjectUtils#toString(Object)
   * @see String#valueOf(Object)
   * @param str  the String to check, may be null
   * @return the passed in String, or the empty String if it
   *  was <code>null</code>
   */
  public static String defaultString(String str)
  {
    return str == null ? EMPTY : str;
  }

  public static String defaultString(Object o)
  {
     return o == null ? EMPTY : o.toString();
  }

  /**
   * <p>Returns either the passed in String, or if the String is
   * <code>null</code>, the value of <code>defaultStr</code>.</p>
   *
   * <pre>
   * StringUtils.defaultString(null, "NULL")  = "NULL"
   * StringUtils.defaultString("", "NULL")    = ""
   * StringUtils.defaultString("bat", "NULL") = "bat"
   * </pre>
   *
   * @see ObjectUtils#toString(Object,String)
   * @see String#valueOf(Object)
   * @param str  the String to check, may be null
   * @param defaultStr  the default String to return
   *  if the input is <code>null</code>, may be null
   * @return the passed in String, or the default if it was <code>null</code>
   */
  public static String defaultString(String str, String defaultStr)
  {
    return str == null ? defaultStr : str;
  }

  /**
   * <p>Returns either the passed in String, or if the String is
   * empty or <code>null</code>, the value of <code>defaultStr</code>.</p>
   *
   * <pre>
   * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
   * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
   * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
   * </pre>
   *
   * @see StringUtils#defaultString(String, String)
   * @param str  the String to check, may be null
   * @param defaultStr  the default String to return
   *  if the input is empty ("") or <code>null</code>, may be null
   * @return the passed in String, or the default
   */
  public static String defaultIfEmpty(String str, String defaultStr)
  {
    return isEmpty(str) ? defaultStr : str;
  }

  // Equals
  //-----------------------------------------------------------------------
  /**
   * <p>Compares two Strings, returning <code>true</code> if they are equal.</p>
   *
   * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
   * references are considered to be equal. The comparison is case sensitive.</p>
   *
   * <pre>
   * StringUtils.equals(null, null)   = true
   * StringUtils.equals(null, "abc")  = false
   * StringUtils.equals("abc", null)  = false
   * StringUtils.equals("abc", "abc") = true
   * StringUtils.equals("abc", "ABC") = false
   * </pre>
   *
   * @see java.lang.String#equals(Object)
   * @param str1  the first String, may be null
   * @param str2  the second String, may be null
   * @return <code>true</code> if the Strings are equal, case sensitive, or
   *  both <code>null</code>
   */
  public static boolean equals(String str1, String str2)
  {
    return str1 == null ? str2 == null : str1.equals(str2);
  }

  /**
   * <p>Compares two Strings, returning <code>true</code> if they are equal ignoring
   * the case.</p>
   *
   * <p><code>null</code>s are handled without exceptions. Two <code>null</code>
   * references are considered equal. Comparison is case insensitive.</p>
   *
   * <pre>
   * StringUtils.equalsIgnoreCase(null, null)   = true
   * StringUtils.equalsIgnoreCase(null, "abc")  = false
   * StringUtils.equalsIgnoreCase("abc", null)  = false
   * StringUtils.equalsIgnoreCase("abc", "abc") = true
   * StringUtils.equalsIgnoreCase("abc", "ABC") = true
   * </pre>
   *
   * @see java.lang.String#equalsIgnoreCase(String)
   * @param str1  the first String, may be null
   * @param str2  the second String, may be null
   * @return <code>true</code> if the Strings are equal, case insensitive, or
   *  both <code>null</code>
   */
  public static boolean equalsIgnoreCase(String str1, String str2)
  {
    return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
  }

  /**
   * <p>Escapes the characters in a <code>String</code> to be suitable to pass to
   * an SQL query.</p>
   *
   * <p>For example,
   * <pre>statement.executeQuery("SELECT * FROM MOVIES WHERE TITLE='" +
   *   StringEscapeUtils.escapeSql("McHale's Navy") +
   *   "'");</pre>
   * </p>
   *
   * <p>At present, this method only turns single-quotes into doubled single-quotes
   * (<code>"McHale's Navy"</code> => <code>"McHale''s Navy"</code>). It does not
   * handle the cases of percent (%) or underscore (_) for use in LIKE clauses.</p>
   *
   * @param str  the string to escape, may be null
   * @return a new String, escaped for SQL, <code>null</code> if null string input
   */
  public static String escapeSql(String str)
  {
    if(str == null)
    {
      return null;
    }
    return replace(str, "'", "''");
  }
  //检查数组里面是否包含某个值



  public static boolean ifContainsInArray(String[] array,String value)
  {
    boolean ifSuc = false;
    for (int i=0;i<array.length;i++)
    {
      if (array[i].equals(value))
      {
        ifSuc = true;
        break;
      }
    }
    return ifSuc;
  }
}
