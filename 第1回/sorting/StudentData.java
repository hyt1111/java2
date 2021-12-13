package sorting;

import java.util.Scanner ;

//�w���̃f�[�^��\���N���X�i�b����̍\���݂̂����Ȃ��́j
public class StudentData {
    private int id, score ;
    private String name, address ;

    // �����Ȃ��R���X�g���N�^
    public StudentData( ) {
    } //�����Ȃ��R���X�g���N�^�̏I���


    // ��������R���X�g���N�^
    public StudentData(int id, String name, String address, int score) {
          this.id = id;
          this.name = name;
          this.address = address;
          this.score = score;
    } //��������R���X�g���N�^�̏I���

    public int getID( ) { // �Q�b�^�[���\�b�h
        return id ;
    }

    public int getScore( ) { // �Q�b�^�[���\�b�h
        return score ;
    }

    public String getName( ) { // �Q�b�^�[���\�b�h
        return name ;
    }

    public String getAddress( ) { // �Q�b�^�[���\�b�h
        return address ;
    }

    public void setID(int id) { // �Z�b�^�[���\�b�h
    	//�{����id�̐������𒲂ׂāC�����̂Ƃ��̂݉��L�̑�����s��
        this.id = id ;
    }

    public void setScore(int score) { // �Z�b�^�[���\�b�h
    	if (score < 0 || score > 100)
    		throw new IllegalArgumentException() ;
        this.score = score ;
    }

    public void setName(String name) { // �Z�b�^�[���\�b�h
    	if (name == null)
    		throw new IllegalArgumentException() ;
        this.name = name ;
    }

    public void setAddress(String address) { // �Z�b�^�[���\�b�h
        this.address = address ;
    }

    @SuppressWarnings("resource")
    public void inputStudentData( ) { // �w���f�[�^����͂��郁�\�b�h
        Scanner  sc = new Scanner(System.in);

        System.out.printf("id: ") ;
        id = Integer.parseInt( sc.nextLine( ) ) ;
        System.out.printf("name: ") ;
        name = sc.nextLine( ) ;
        System.out.printf("address: ") ;
        address = sc.nextLine( ) ;
        System.out.printf("score: ") ;
        score = Integer.parseInt( sc.nextLine( ) ) ;
    } // �w���f�[�^����͂��郁�\�b�h�̏I���

    public void printStudentData( ) { // �w���f�[�^��\�����郁�\�b�h
        System.out.println("id: " + id) ;
        System.out.println("name: " + name) ;
        System.out.println("address: " + address) ;
        System.out.println("score: " + score) ;
    } // �w���f�[�^����͂��郁�\�b�h�̏I���
} // StudentData�N���X�̏I���

