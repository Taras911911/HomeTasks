package ru.kpfu.itis.group11506.semestr;

public class LongArithmetic {

    public static final int BASE_MULTIPLY = 8;
    private String result;

    public String addition(String stringNumber1, String stringNumber2) throws NotANumberExeption {
        int lengthSum = stringNumber1.length() + stringNumber2.length();

        StringTransformer stringTransformer = new StringTransformer(stringNumber1, stringNumber2).invoke(
                lengthSum, lengthSum);
        boolean isANegative = stringTransformer.isANegative();
        boolean isBNegative = stringTransformer.isBNegative();
        byte[] byteNumber1 = stringTransformer.getByteNumber1();
        byte[] byteNumber2 = stringTransformer.getByteNumber2();
        if (isANegative && !isBNegative) {
            stringNumber1 = stringNumber1.replace("-", "");
            result = subtraction(stringNumber2, stringNumber1);
        } else if (!isANegative && isBNegative) {
            stringNumber2 = stringNumber2.replace("-", "");
            result = subtraction(stringNumber1, stringNumber2);
        } else {
            if (isANegative) {
                result = toString(true, add(byteNumber1, byteNumber2));
            } else {
                result = toString(false, add(byteNumber1, byteNumber2));
            }
        }
        return result;
    }

    private byte[] add(byte[] byteNumber1, byte[] byteNumber2) {
        byte[] anotherArray = new byte[byteNumber1.length + byteNumber2.length];
        System.arraycopy(byteNumber2, 0, anotherArray, 0, byteNumber2.length);
        byte surplus = 0;
        byte[] resultation = new byte[byteNumber1.length + anotherArray.length];
        System.arraycopy(byteNumber1, 0, resultation, 0, byteNumber1.length);
        for (int i = 0; i < anotherArray.length; i++) {
            byte sum = (byte) (resultation[i] + anotherArray[i]);
            resultation[i] = (byte) ((surplus + sum) % 10);
            surplus = (byte) ((sum + surplus) / 10);
        }
        String t = toString(false, resultation);
        resultation = new byte[t.length()];
        for (int i = t.length() - 1; i > -1; i--) {
            int j11 = t.length() - i - 1;
            resultation[i] = (byte) Character.getNumericValue(t.charAt(j11));
        }
        return resultation;
    }

    public String subtraction(String stringNumber1, String stringNumber2) throws NotANumberExeption {
        int lengthSum = stringNumber1.length() + stringNumber2.length();
        StringTransformer stringTransformer = new StringTransformer(stringNumber1, stringNumber2).invoke(
                lengthSum, lengthSum);
        boolean isANegative = stringTransformer.isANegative();
        boolean isBNegative = stringTransformer.isBNegative();
        byte[] byteNumber1 = stringTransformer.getByteNumber1();
        byte[] byteNumber2 = stringTransformer.getByteNumber2();
        if (isANegative && !isBNegative) {
            result = toString(true, add(byteNumber1, byteNumber2));
        } else if (!isANegative && isBNegative) {
            result = toString(false, add(byteNumber1, byteNumber2));
        } else {
            if (isANegative) {
                byte[] helper = byteNumber1;
                byteNumber1 = byteNumber2;
                byteNumber2 = helper;
            }
            if (compare(byteNumber1, byteNumber2) == 1 || compare(byteNumber1, byteNumber2) == 0) {
                result = toString(false, deduct(byteNumber1, byteNumber2));
            } else {
                result = toString(true, deduct(byteNumber2, byteNumber1));
            }
        }
        return result;
    }

    private byte[] deduct(byte[] byteNumber1, byte[] byteNumber2) {
        byte[] anotherArray = new byte[byteNumber1.length + byteNumber2.length];
        System.arraycopy(byteNumber2, 0, anotherArray, 0, byteNumber2.length);
        int surplus = 0;
        byte[] resultation = new byte[byteNumber1.length + anotherArray.length];
        System.arraycopy(byteNumber1, 0, resultation, 0, byteNumber1.length);
        for (int i = 0; i < anotherArray.length; i++) {
            byte j = (byte) (resultation[i] - anotherArray[i]);
            if (j < 0) {
                if (resultation[i + 1] == 0) {
                    while (resultation[i + 1 + surplus] == 0) {
                        resultation[i + 1 + surplus] = 9;
                        surplus += 1;
                    }
                    resultation[i + 1 + surplus] -= 1;
                    surplus = 0;
                } else {
                    resultation[i + 1] -= 1;
                }
                resultation[i] = (byte) (10 + j);
            } else {
                resultation[i] = j;
            }
        }
        String t = toString(false, resultation);
        resultation = new byte[t.length()];
        for (int i = t.length() - 1; i > -1; i--) {
            int j11 = t.length() - i - 1;
            resultation[i] = (byte) Character.getNumericValue(t.charAt(j11));
        }
        return resultation;
    }


    private String toString(boolean needNegative, byte[] resultation) {
        int index = 0;
        String byteNumber1;
        if (needNegative) {
            byteNumber1 = "-";
        } else {
            byteNumber1 = "";
        }
        for (int i = resultation.length - 1; i > -1; i--) {
            if (resultation[i] != 0) {
                index = i;
                break;
            }
        }
        for (int i = index; i > -1; i--) {
            byteNumber1 = byteNumber1 + resultation[i];
        }
        return byteNumber1;
    }

    private int compare(byte[] byteNumber1, byte[] byteNumber2) {
        if (byteNumber1.length > byteNumber2.length) {
            return 1;
        } else if (byteNumber1.length < byteNumber2.length) {
            return -1;
        } else {
            for (int i = byteNumber1.length - 1; i > -1; i--) {
                if (byteNumber1[i] > byteNumber2[i]) {
                    return 1;
                } else if (byteNumber1[i] < byteNumber2[i]) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public String multiplication(String stringNumber1, String stringNumber2) throws NotANumberExeption {
        StringTransformer stringTransformer = new StringTransformer(stringNumber1, stringNumber2).invoke(
                stringNumber1.length(), stringNumber2.length());
        boolean isANegative = stringTransformer.isANegative();
        boolean isBNegative = stringTransformer.isBNegative();
        byte[] byteNumber1 = stringTransformer.getByteNumber1();
        byte[] byteNumber2 = stringTransformer.getByteNumber2();
        if ((isANegative && isBNegative) || (!isANegative) && (!isBNegative)) {
            result = toString(false, multiply(byteNumber1, byteNumber2));
        } else {
            result = toString(true, multiply(byteNumber1, byteNumber2));
        }
        return result;
    }

    // byteNumber1 * byteNumber2 = a0*b0 + ((a00 + a1) * (b00 + b1) - a0*b0 - a1*b1) * 10^(BASE_MULTIPLY)+
    // + a1*b1*10^(BASE_MULTIPLY*2)
    private byte[] multiply(byte[] byteNumber1, byte[] byteNumber2) {
        long a0, b0;
        byte[] a0b0, a1b1, a0a1b0b1, x, y, a1, b1, a00, b00;
        String substringForInitA00, substringForInitB00;
        if (byteNumber1.length > BASE_MULTIPLY) {
            a0 = Long.parseLong(toString(false, byteNumber1).substring(
                    toString(false, byteNumber1).length() - BASE_MULTIPLY, toString(false, byteNumber1).length()));
            a1 = new byte[byteNumber1.length - BASE_MULTIPLY];
            System.arraycopy(byteNumber1, BASE_MULTIPLY, a1, 0, byteNumber1.length - BASE_MULTIPLY);
            substringForInitA00 = toString(false, byteNumber1).substring(
                    toString(false, byteNumber1).length() - BASE_MULTIPLY, toString(false, byteNumber1).length());
            a00 = new byte[substringForInitA00.length()];
            for (int i = substringForInitA00.length() - 1; i > -1; i--) {
                int m = substringForInitA00.length() - i - 1;
                a00[i] = (byte) Character.getNumericValue(substringForInitA00.charAt(m));
            }
        } else {
            a0 = byteArraytoLong(byteNumber1);
            a1 = new byte[]{0};
            a00 = longToByteArray(a0);
        }
        if (byteNumber2.length > BASE_MULTIPLY) {
            b0 = Long.parseLong(toString(false, byteNumber2).substring(
                    toString(false, byteNumber2).length() - BASE_MULTIPLY, toString(false, byteNumber2).length()));
            b1 = new byte[byteNumber2.length - BASE_MULTIPLY];
            System.arraycopy(byteNumber2, BASE_MULTIPLY, b1, 0, byteNumber2.length - BASE_MULTIPLY);
            substringForInitB00 = toString(false, byteNumber2).substring(
                    toString(false, byteNumber2).length() - BASE_MULTIPLY, toString(false, byteNumber2).length());
            b00 = new byte[substringForInitB00.length()];
            for (int i = substringForInitB00.length() - 1; i > -1; i--) {
                int m = substringForInitB00.length() - i - 1;
                b00[i] = (byte) Character.getNumericValue(substringForInitB00.charAt(m));
            }
        } else {
            b0 = byteArraytoLong(byteNumber2);
            b1 = new byte[]{0};
            b00 = longToByteArray(b0);
        }
        a0b0 = longToByteArray(a0 * b0);
        if (add(a00, a1).length > BASE_MULTIPLY || add(b00, b1).length > BASE_MULTIPLY) {
            a0a1b0b1 = multiply(add(a00, a1), add(b00, b1));
        } else {
            a0a1b0b1 = longToByteArray(byteArraytoLong(add(a00, a1)) * byteArraytoLong(add(b00, b1)));
        }
        if (a1.length > BASE_MULTIPLY || b1.length > BASE_MULTIPLY) {
            a1b1 = multiply(a1, b1);
        } else {
            a1b1 = longToByteArray(byteArraytoLong(a1) * byteArraytoLong(b1));
        }
        x = getZeros(BASE_MULTIPLY, deduct((deduct(a0a1b0b1, a0b0)), a1b1));
        y = getZeros(BASE_MULTIPLY * 2, a1b1);

        return add(add(a0b0, x), y);
    }

    private byte[] longToByteArray(long l) {
        byte[] byteNumber1;
        String string = Long.toString(l);
        byteNumber1 = new byte[string.length()];
        for (int i = string.length() - 1; i > -1; i--) {
            int j = string.length() - i - 1;
            byteNumber1[i] = (byte) Character.getNumericValue(string.charAt(j));
        }
        return byteNumber1;
    }

    private long byteArraytoLong(byte[] array) {
        return Long.parseLong(toString(false, array));

    }


    private byte[] getZeros(int zeroLength, byte[] byteNumber2) {
        byte[] resultation = new byte[byteNumber2.length + zeroLength];
        System.arraycopy(byteNumber2, 0, resultation, zeroLength, byteNumber2.length);
        return resultation;
    }

    String degree(int n, String result) throws NotANumberExeption {
        String s = result;
        while (n > 1) {
            s = multiplication(s, result);
            n -= 1;
        }
        return s;
    }

    public String leftLogicalShift(int n, String result) throws NotANumberExeption {
        long l = (long) Math.pow(2d, (double) n);
        return multiplication(toString(false, longToByteArray(l)), result);
    }


    private byte[] convertToArray(int begin, String one) { //преобразует string в байтовый массив
        byte[] byteNumber1 = new byte[one.length() - begin];
        for (int i = begin; i < one.length(); i++) {
            byteNumber1[i - begin] = Byte.valueOf(String.valueOf(one.charAt(i)));
        }
        return byteNumber1;
    }

    private boolean isNumber(String string) {
        boolean flag = true;
        int chislo = Character.getNumericValue(string.charAt(0));
        if (chislo > 9 || (string.charAt(0) != '-' && chislo == -1)) {
            flag = false;
        }
        for (int i = 1; i < string.length(); i++) {
            chislo = Character.getNumericValue(string.charAt(i));
            if(flag && chislo > 9 && chislo > -1)
                flag = false;
        }

        return flag;
    }

    public byte[] division(String first, String second) throws NotANumberExeption { //деление
        if (!isNumber(first) || !isNumber(second)) {
            NotANumberExeption e = new NotANumberExeption("You're such an idiot!! It was not a number!");
            throw e;
        }
        boolean isNegative = false;
        if ((first.charAt(0) == '-' && second.charAt(0) != '-') || (first.charAt(0) != '-'&& second.charAt(0) == '-')){
            isNegative = true;
        }
        byte[] one;
        if (first.charAt(0) == '-') {
            one = convertToArray(1, first);
        } else {
            one = convertToArray(0, first);
        }
        byte[] two;
        if (second.charAt(0) == '-') {
            two = convertToArray(1, second);
        } else {
            two = convertToArray(0, second);
        }
        if (one.length >= two.length && isBigger(one, two)) { //если 1 число больше 2
            byte[] result= new byte[one.length - two.length + 1];//вводим байтовый массив-результат
            for (int i = 0; one.length - i >= two.length; i++) {//отделяем СЛЕВА от делимого количество символов как в делителе
                int k = i - 1;
                int plus = 0;
                int may = 0;
                while (k != -1) { //проверка - сколько ненулевых разрядов осталось после предыдущего вычитания
                    if (one[k] != 0) {
                        plus++;
                        plus += may;
                        may = 0;
                    } else {
                        may++;
                    }
                    k--;
                }
                byte[] stringNumber1 = subArray(one, i - plus, two.length + plus); //подмассив
                if (isBigger(stringNumber1, two)) { //если подмассив > числа на которе делю то заходим в 1.1
                    byte count = 2; //1.1
                    byte[] mult = multiply(two, count);
                    while (!isBigger(mult, stringNumber1) || isEqual(mult, stringNumber1)) {
                        count++;//множитель для делителя чтобы вычитаемое было с мин. остатком
                        mult = multiply(two, count);
                    }
                    count--;
                    if ((i + 1) == result.length) { // добавляет разряд в ответ
                        System.arraycopy(result, 1, result, 0, result.length - 1);
                        result[result.length - 1] = count;
                    } else
                        result[i + 1] = count;
                    stringNumber1 = subtraction(stringNumber1, multiply(two, count)); // вычитание
                    for (k = i; k < stringNumber1.length + i; k++) { // заменяем на следующий делитель
                        one[k - plus] = stringNumber1[k - i];
                    }
                } else { // если не хватило одного добавленного разряда записываем в ответе 0 и спускаем еще один разряд
                    if ((i + 1) == result.length) {
                        System.arraycopy(result, 1, result, 0, result.length - 1);
                        result[result.length - 1] = 0;
                    } else
                        result[i + 1] = 0;
                }
            }
            if (isNegative) {
                byte[] res = new byte[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    res[i+1] = result[i];
                }
                result = res;
                result[0] = -1;
            }
            return result;
        } else {
            return new byte[1]; //если делитель больше делимого ответ - 0
        }

    }

    private byte[] subArray(byte[] bigOne, int startIndex, int size) { // принимаем на вход массив и делим его на подмассиввы длинны делителя или делителя+1
        int c = startIndex;
        while (size > 0 && bigOne[c] == 0) {
            size--;
            startIndex++;
            c++;
        }
        if (size != 0) {
            byte[] result = new byte[size]; // записвываем оставшиеся ненелевые разряды
            System.arraycopy(bigOne, startIndex, result, 0, size);
            return result;
        } else
            return new byte[1];

    }


    private byte[] subtraction(byte[] one, byte[] two) {
        if (one.length <= two.length) {
            if (one.length < two.length)
                if (two[0] == 0) {
                    two = subArray(two, 1, two.length - 1);
                }
            for (int i = one.length - 1; i > -1; i--) {
                if (one[i] < two[i]) {
                    int k = i - 1;
                    while (one[k] == 0)
                        k--;
                    one[k]--;
                    k++;
                    while (k != i) {
                        one[k] += 9;
                        k++;
                    }
                    one[i] = (byte) (one[i] + 10 - two[i]);
                } else {
                    one[i] = (byte) (one[i] - two[i]);
                }
            }
        } else {
            for (int i = two.length; i > 0; i--) {
                if (one[i] < two[i - 1]) {
                    int k = i - 1;
                    while (one[k] == 0)
                        k--;
                    one[k]--;
                    k++;
                    while (k != i) {
                        one[k] += 9;
                        k++;
                    }
                    one[i] = (byte) (one[i] + 10 - two[i - 1]);
                } else {
                    one[i] = (byte) (one[i] - two[i - 1]);
                }
            }
        }
        return one;
    }


    private byte[] multiply(byte[] first, byte second) { //умножение на число
        byte[] result = new byte[first.length + 1];
        int remember = 0;// запоминает доп разряды для умножения
        for (int i = first.length; i > 0; i--) {
            result[i] = (byte) (first[i - 1] * second + remember);
            remember = 0;
            if (result[i] > 9) {
                remember = result[i] / 10;
                result[i] = (byte) (result[i] % 10);
            }
        }
        if (remember != 0)
            result[0] = (byte) remember;
        return result;
    }

    private boolean isEqual(byte[] one, byte[] two) {//равны числа или нет(делитель и делимое)
        int i = 0;
        int k = 0;
        while (one[i] == 0) {//пропуск нулей в начале
            i++;
        }
        while (two[k] == 0) {
            k++;
        }
        if ((one.length - i) == (two.length - k)) {
            boolean flag = true;
            while (i < one.length && flag) {
                flag = (one[i] == two[k]);
                i++;
                k++;
            }
            return flag;
        } else
            return false;
    }

    private boolean isBigger(byte[] one, byte[] two) {
        int i = 0;
        if (one.length == two.length) {
            while (i < one.length && one[i] == two[i]) {
                i++;
            }
            return !(i < two.length && one[i] < two[i]);
        } else {
            if (one.length > two.length) {
                int dif = one.length - two.length;

                if (dif == 1 && one[0] != 0) {
                    return true;
                } else {
                    if (dif == 1) {
                        while (one[i + 1] == two[i] && (i + 2) < one.length) {
                            i++;
                        }
                        return !(i < two.length && one[i + 1] < two[i]);
                    } else {
                        for (int l = 0; l < dif; l++) {
                            if (one[l] != 0)
                                return true;
                        }

                        while (one[i + dif] == two[i] && (i + dif) < one.length) {
                            i++;
                        }
                        return !(i < two.length && one[i + dif] < two[i]);
                    }
                }
            } else {
                return false;
            }
        }
    }

    public void showArray(byte[] array) {

        for (byte anArray : array) {
            if (anArray == -1) {
                System.out.print('-');
            } else {
                System.out.print(anArray);
            }
        }
    }

    public void rightLogicalShift(int n, String result) throws NotANumberExeption {
        showArray(division(result, degree(n, "2")));
    }

    private class StringTransformer {
        private String stringNumber1;
        private String stringNumber2;
        private byte[] byteNumber1;
        private byte[] byteNumber2;
        private boolean isANegative;
        private boolean isBNegative;

        public StringTransformer(String stringNumber1, String stringNumber2) {
            this.stringNumber1 = stringNumber1;
            this.stringNumber2 = stringNumber2;
        }

        public byte[] getByteNumber1() {
            return byteNumber1;
        }

        public byte[] getByteNumber2() {
            return byteNumber2;
        }

        public boolean isANegative() {
            return isANegative;
        }

        public boolean isBNegative() {
            return isBNegative;
        }

        public StringTransformer invoke(int length, int length2) throws NotANumberExeption {

            isANegative = false;
            isBNegative = false;
            byteNumber1 = new byte[length];
            if (stringNumber1.length() == 0 || stringNumber2.length() == 0) {
                throw new NotANumberExeption("Not a Number");
            }
            char number = stringNumber1.charAt(0);
            if (number != '-' && number != '1' && number != '2' && number != '3' && number != '4' &&
                    number != '5' && number != '6' && number != '7' && number != '8' &&
                    number != '9' && number != '0') {
                throw new NotANumberExeption("Not a Number");
            }
            number = stringNumber2.charAt(0);
            if (number != '-' && number != '1' && number != '2' && number != '3' && number != '4' &&
                    number != '5' && number != '6' && number != '7' && number != '8' &&
                    number != '9' && number != '0') {
                throw new NotANumberExeption("Not a Number");
            }

            for (int i = 1; i < stringNumber1.length(); i++) {
                number = stringNumber1.charAt(i);
                if (number != '1' && number != '2' && number != '3' && number != '4' &&
                        number != '5' && number != '6' && number != '7' && number != '8' &&
                        number != '9' && number != '0') {
                    throw new NotANumberExeption("Not a Number");
                }
            }
            for (int i = 1; i < stringNumber2.length(); i++) {
                number = stringNumber2.charAt(i);
                if (number != '1' && number != '2' && number != '3' && number != '4' &&
                        number != '5' && number != '6' && number != '7' && number != '8' &&
                        number != '9' && number != '0') {
                    throw new NotANumberExeption("Not a Number");
                }
            }


            for (int i = stringNumber1.length() - 1; i > -1; i--) {
                int j = stringNumber1.length() - i - 1;
                byteNumber1[i] = (byte) Character.getNumericValue(stringNumber1.charAt(j));
                if (byteNumber1[i] == -1) {
                    isANegative = true;
                    byteNumber1[i] = 0;
                }
            }
            byteNumber2 = new byte[length2];
            for (int i = stringNumber2.length() - 1; i > -1; i--) {
                int j = stringNumber2.length() - i - 1;
                byteNumber2[i] = (byte) Character.getNumericValue(stringNumber2.charAt(j));
                if (byteNumber2[i] == -1) {
                    isBNegative = true;
                    byteNumber2[i] = 0;
                }
            }
            return this;
        }
    }
}


