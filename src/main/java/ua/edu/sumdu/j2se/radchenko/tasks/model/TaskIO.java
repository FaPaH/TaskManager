package ua.edu.sumdu.j2se.radchenko.tasks.model;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out){

        try(DataOutputStream dos = new DataOutputStream(out)){
            tasks.getStream().forEach(task -> {
                try {
                    dos.writeInt(tasks.size());
                    dos.writeInt(task.getTitle().length());
                    dos.writeUTF(task.getTitle());
                    dos.writeBoolean(task.isActive());
                    dos.writeInt(task.getRepeatInterval());

                    if (task.isRepeated()) {
                        dos.writeInt(task.getStartTime().getNano());
                        dos.writeInt(task.getEndTime().getNano());
                    } else {
                        dos.writeInt(task.getTime().getNano());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in){
        try(DataInputStream dis = new DataInputStream(in)){
            tasks.getStream().forEach(task -> {
                try {
                    int size = dis.readInt();
                    int titleLength = dis.readInt();
                    String title = dis.readUTF();
                    boolean isActive = dis.readBoolean();
                    int repeatInterval = dis.readInt();

                    if (repeatInterval != 0){
                        LocalDateTime start = LocalDateTime.ofEpochSecond(dis.readInt(),0 , ZoneOffset.UTC);
                        LocalDateTime end = LocalDateTime.ofEpochSecond(dis.readInt(),0 , ZoneOffset.UTC);

                        Task repeatTask = new Task(title, start, end, repeatInterval);
                        repeatTask.setActive(isActive);
                        tasks.add(repeatTask);
                    } else {
                        LocalDateTime time = LocalDateTime.ofEpochSecond(dis.readInt(),0 , ZoneOffset.UTC);

                        Task nonRepeatTask = new Task(title, time);
                        nonRepeatTask.setActive(isActive);
                        tasks.add(nonRepeatTask);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            write(tasks, bw);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            read(tasks, br);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gsonWrite = new Gson();
        gsonWrite.toJson(tasks, out);
        out.flush();
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        Gson gsonRead = new Gson();
        AbstractTaskList taskHolder = gsonRead.fromJson(in, tasks.getClass());
        for (Task task : taskHolder) {
            tasks.add(task);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try(FileOutputStream fos = new FileOutputStream(file)){
            write(tasks, fos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try(FileInputStream fos = new FileInputStream(file)){
            read(tasks, fos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
