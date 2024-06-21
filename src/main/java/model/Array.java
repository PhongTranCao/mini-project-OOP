package model;

public interface Array<E> {
    boolean insert(E e);
    boolean delete();
    boolean search(E e);
    boolean sort();
    boolean isEmpty();
    int getSize();
}

// interface de lam gi
// bat buoc phai public boi vi m phai su dung
// cai nay de lap nen cac class khac


// abstract class de lam gi
// kh bat b
// cai nay de lap nen cac class khac


// interface khac voi abstract class o diem nao

// giong nhau
// hinh dung ca 2 nhu mot ban blueprint de in ra cac doi tuong
// kh dc la protected hoac private

// khac nhau
// interface
// chi dc khai bao ten cua method, khong co noi dung ben trong
// 1 class co the implement nhieu interface
// class con bat buoc phai co du cac method trong interface
// -> bat buoc mot moi quan he 'class con' "is a" 'interface'

// abstract class
// khong bat buoc phai nhu vay
// 1 class chi co the extends 1 abstract class
// class con co the su dung 1 hoac khong method nao trong abstract class
// -> khong bat buoc mot moi quan he 'class con' "is a" 'interface'