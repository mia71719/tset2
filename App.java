public static void main(String[] args) throws Exception 
{
    String inputString = "ABC123";
    StringBuilder newStr = new StringBuilder();
    byte[] array = inputString.getBytes(StandardCharsets.UTF_8);
    
    for (int i = array.length - 1; i >= 0; i--) 
        {     newStr.append((char) (array[i] - 1));
    System.out.println((char) (array[i] - 1)));
        }String result = newStr.toString();
    System.out.println("Transformed String: " + result);
    boolean matches = Pattern.matches("[A-Z]{3}[0-9]{3}", inputString);
    System.out.println("Matches pattern [A-Z]{3}[0-9]{3}: " + matches);
    if(result == "ZAB012") {
        System.out.println("Transformation is correct.");
    } else {
        System.out.println("Transformation is incorrect.");
    }
}
