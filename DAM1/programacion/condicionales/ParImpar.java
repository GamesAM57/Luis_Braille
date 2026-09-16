Isbn varchar2(10) primary key,
titulo nombre son varchar2(30),
temática varchar2(20),
posición varchar2(5) like (EST-_),
temáticas varchar2(10) IN ('terror', 'aventuras', 'infantil')


posición se refiere a la estantería donde está el libro, y la forma de nombrarla es ‘EST-x’, siendo x un
número o letra identificativa (1 carácter). Por ejemplo: EST-1, EST-2, EST-a, EST-H, etc