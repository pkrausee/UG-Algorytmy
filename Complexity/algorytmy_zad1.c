#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#define MAX 60000l
#define MLD 1000000000.0


void utworz_MACIERZ(int n, int ***M)
{
	// alokujĂŞ pamiĂŞĂ¦ na tablicĂŞ rozmiaru nxn
	// i wpisuje losowe wartoÂści 0/1 w macierzy
	int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++)
	{
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++)
	{
        for(j=0;j<n;j++)
		{
			(*M)[i][j]=rand()% 2;
        }
    }
}


void utworz_MACIERZzerowa(int n, int ***M)
{
	// alokujĂŞ pamiĂŞĂ¦ na tablicĂŞ rozmiaru nxn
	// i wpisuje losowe wartoÂści 0 w macierzy
	int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++)
	{
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++)
	{
        for(j=0;j<n;j++)
		{
			(*M)[i][j]=0;
        }
    }
}


void utworz_MACIERZjedynkowa(int n, int ***M)
{
	// alokujĂŞ pamiĂŞĂ¦ na tablicĂŞ rozmiaru nxn
	// i wpisuje losowe wartoÂści 1 w macierzy
	int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++)
	{
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++)
	{
        for(j=0;j<n;j++)
		{
            (*M)[i][j]=1;
        }
    }
}


void utworz_MACIERZ_x(int n, int ***M, int x){
	// alokujĂŞ pamiĂŞĂ¦ na tablicĂŞ rozmiaru nxn
	// i wpisuje do macierzy wszĂŞdzie wartoÂści x
	int i,j;
    (*M) = (int **)malloc(n*sizeof(int *));
    for(i=0;i<n;i++)
	{
        (*M)[i]=(int *)malloc(n*sizeof(int));
    }
    for(i=0;i<n;i++)
	{
        for(j=0;j<n;j++)
		{
            (*M)[i][j]=x;
        }
    }
}


void wypisz_MACIERZ(int n, int **M)
{
	// wypisuje wartoÂści macierzy
	int i,j;
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
			printf("%d",M[i][j]);
		printf("\n");
    }
}


void zwolnij_MACIERZ(int n, int **M)
{
	// zwalania pamiĂŞĂ¦ zarezerwowanáą— dla macierzy
	int i;
    for(i=0;i<n;i++)
    {
		free(M[i]);
    }
    free(M);
}


//ALGORYTM PIERWSZY
int ALGO_NAIWNY(int n, int **M)
{
	int x1,y1,x2,y2,x,y;
	int max=0;
	int local_max=0;

	for(x1=0;x1<n;x1++)
		for(y1=0;y1<n;y1++)
			for(x2=n-1;x2>x1-1;x2--)
				for(y2=n-1;y2>y1-1;y2--)
				{
					local_max=0;
					for(x=x1;x<x2+1;x++)
						for(y=y1;y<y2+1;y++)
							local_max+=M[x][y];
					if ((local_max==(x2-x1+1)*(y2-y1+1)) && (local_max>max)) 
						max=local_max;
				}
	return max;
}


//ALGORYTM DRUGI
int REKURENCJA(int **M,int x1, int y1, int x2, int y2)
{
	if ((x2==x1)&&(y2==y1)) 
		return M[x1][y1];
	else if ((x2-x1)>(y2-y1))
		return REKURENCJA(M,x1,y1,(int)(x1+x2)/2,y2)*REKURENCJA(M,(int)(x1+x2+1)/2,y1,x2,y2);
	else 
		return REKURENCJA(M,x1,y1,x2,(int)(y1+y2)/2)*REKURENCJA(M,x1,(int)(y1+y2+1)/2,x2,y2);
}

int ALGO_REKURENCYJNY(int n, int **M)
{
	int x1,y1,x2,y2;
	int max=0;
	int local_max;

	for(x1=0;x1<n;x1++)
		for(y1=0;y1<n;y1++)
			for(x2=x1;x2<n;x2++)
				for(y2=y1;y2<n;y2++)
				{
					local_max=REKURENCJA(M,x1,y1,x2,y2)*(x2-x1+1)*(y2-y1+1);
					if (local_max>max) 
						max=local_max;
				}
	return max;
}


//ALGORYTM TRZECI
int ALGO_DYNAMICZNY(int n, int **M)
{
	int x1,x2,y;
	int max=0;
	int iloczyn;
	int **MM;

	utworz_MACIERZ_x(n,&MM,0);

	for(y=0;y<n;y++)
		for(x1=0;x1<n;x1++)
		{
			iloczyn=1;
			for(x2=x1;x2<n;x2++)
			{
				iloczyn*=M[x2][y];
				MM[x1][x2]=iloczyn*(x2-x1+1+MM[x1][x2]);
				if (MM[x1][x2]>max) 
					max=MM[x1][x2];
			}
		}
	return max;
}


//ALGORYTM CZWARTY
int ALGO_CZULY(int n, int **M)
{
	int x1,y1,x2,y2,ymax;
	int max=0;
	int local_max=0;

	for(x1=0;x1<n;x1++)
		for(y1=0;y1<n;y1++)
		{
			local_max=0;
			x2=x1;
			ymax=n-1;
			while ((x2<n)&&(M[x2][y1]==1))
			{
				y2=y1;
				while((y2<ymax+1)&&(M[x2][y2]==1))
				{
					y2++;
				}
				ymax=y2-1;
				local_max=(x2-x1+1)*(ymax-y1+1);
				if (local_max>max) 
					max=local_max;
				x2++;
			}
		}
	return max;
}



int main()
{
    int **Macierz;
    srand(time(NULL));
    long int n;
    struct timespec tp0, tp1;
    double Tn,Fn,x;

    //ALGORYTM PIERWSZY
	//Algorytm posiada 4 zagniezdzone petle kazda z czasem wykonania n oraz 2 wykonujace sie n razy ze zlozonoscia zalezna od poprzednich petli,
	//wiec jego zlozonosc bedzie wynosila n^5 + log2(n) + sqrt(n)
	//Funcja tworzenia macierzy nie ma znaczenia
	printf("\nALGORYTM PIERWSZY(NAIWNY): Fn = n^5 + log2(n) + sqrt(n)\n");
    for (n=10; n<50; n+=5) 
	{
		utworz_MACIERZ(n, &Macierz);
        //utworz_MACIERZzerowa(n,&Macierz);
        //utworz_MACIERZjedynkowa(n,&Macierz);

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_NAIWNY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        Fn=pow(n,5) * log2(n) * sqrt(n);
        
        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }

    //ALGORYTM DRUGI
	//Algorytm posiada 4 zagniezdzone petle kazda z czasem wykonania n oraz jedno wywołanie funkcji rekurencyjnej w srodku o zlozonosci n^2,
	//wiec jego zlozonosc bedzie wynosila n^6
	//Funcja tworzenia macierzy nie ma znaczenia
	printf("\nALGORYTM DRUGI(REKURENCYJNY): Fn = n^6\n");
    for (n=10; n<50; n+=5) 
	{
		utworz_MACIERZ(n, &Macierz);
        //utworz_MACIERZzerowa(n,&Macierz);
        //utworz_MACIERZjedynkowa(n,&Macierz);

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_REKURENCYJNY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        Fn=pow(n,6);
        
        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }
     
    //ALGORYTM TRZECI
	//Algorytm posiada 3 zagniezdzone petle kazda z czasem wykonania n, wiec jego zlozonosc bedzie wynosila n^3
	//Funcja tworzenia macierzy nie ma znaczenia
	printf("\nALGORYTM TRZECI(DYNAMICZNY): Fn = n^3\n");
    for (n=100; n<250; n+=25) 
	{
        utworz_MACIERZ(n,&Macierz);
        //utworz_MACIERZzerowa(n,&Macierz);
        //utworz_MACIERZjedynkowa(n,&Macierz);

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_DYNAMICZNY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        Fn=pow(n,3);

        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }

    //ALGORYTM CZWARTY
	//Algorytm posiada 2 zagniezdzone petle kazda z czasem wykonania n, oraz 2 zagniezdzone petle while. Wynik optimistyczny - n^2, pesymistyczny - n^4
	//Petle while nie wykonaja sie dla macierzy zerowej
	printf("\nALGORYTM CZWARTY(CZULY): Fn = n^2 dla optymistycznego i Fn = n^4 dla pesymistycznego\n");
    for (n=100; n<250; n+=25) 
	{
        //utworz_MACIERZ(n,&Macierz);
        //utworz_MACIERZzerowa(n,&Macierz); //Optymistyczny
        utworz_MACIERZjedynkowa(n,&Macierz); //Pesymistyczny

        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp0);
        ALGO_CZULY(n, Macierz);
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&tp1);

        //Fn = pow(n,2); //Optymistyczny
		Fn = pow(n,4); //Pesymistyczny

        Tn=(tp1.tv_sec+tp1.tv_nsec/MLD)-(tp0.tv_sec+tp0.tv_nsec/MLD);
        printf("n: %5ld \tczas: %3.10lf \twspolczynnik: %3.5lf\n", n, Tn, Fn/Tn);
        zwolnij_MACIERZ(n, Macierz);
    }   


    return 0;
}
