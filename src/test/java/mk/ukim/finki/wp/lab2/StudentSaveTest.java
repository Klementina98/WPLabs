package mk.ukim.finki.wp.lab2;

import mk.ukim.finki.wp.lab2.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab2.Model.Student;
import mk.ukim.finki.wp.lab2.Repository.StudentRepository;
import mk.ukim.finki.wp.lab2.Service.Impl.StudentServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentSaveTest {
    @Mock
    private StudentRepository studentRepository;

    //treba i da se inicijalizira
    private StudentServiceImpl service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Student student = new Student("username","password","name","surname");
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        service = Mockito.spy(new StudentServiceImpl(this.studentRepository));
    }
    @Test
    public void testSuccessSave(){
        Student student = this.service.save("username","password","name","surname");
            //ova e studentot koj sto nie ochekuvame da bide sejvnat
        Mockito.verify(this.service).save("username","password","name","surname");
            //da vidime dali kje bide poviknat save metodot

        //da proverime dali studentot ne e null
        Assert.assertNotNull("Student is null",student);
        Assert.assertEquals("username do not match","username",student.getUsername());
        Assert.assertEquals("password do not match","password",student.getPassword());
        Assert.assertEquals("name do not match","name",student.getName());
        Assert.assertEquals("surname do not match","surname",student.getSurname());
    }
    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidArgumentException", InvalidArgumentException.class,
                () -> this.service.save(null,"password","name","surname"));
        Mockito.verify(this.service).save(null,"password","name","surname");
    }
    @Test
    public void testEmptyUsername(){
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()-> this.service.save(username,"password","name","surname"));
        Mockito.verify(this.service).save(username,"password","name","surname");

    }
    @Test
    public void testEmptyByPassword(){
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",InvalidArgumentException.class,
                ()-> this.service.save(username,password,"name","surname"));
        Mockito.verify(this.service).save(username,password,"name","surname");

    }
    @Test
    public void testNullPassword(){
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException", InvalidArgumentException.class,
                () -> this.service.save(username,password,"name","surname"));
        Mockito.verify(this.service).save(username,password,"name","surname");
    }
}
