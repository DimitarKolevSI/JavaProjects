package Shop;

import java.util.Objects;

public class LaptopAndPcCategory extends Product {
    private String Brand;
    private String Processor;
    private int HD;
    private int RAM;
    private String MotherBoard;
    private String VideoCard;
    public final static Category category = Category.LAPTOP_AND_PC;

    public LaptopAndPcCategory(){
        super();
        setBrand("");
        setProcessor("");
        setHD(0);
        setRAM(0);
        setMotherBoard("");
        setVideoCard("");
    }

    public LaptopAndPcCategory(LaptopAndPcCategory old){
        super(old);
        setBrand(old.Brand);
        setProcessor(old.Processor);
        setHD(old.HD);
        setRAM(old.RAM);
        setMotherBoard(old.MotherBoard);
        setVideoCard(old.VideoCard);
    }

    public LaptopAndPcCategory(Product old, String brand, String processor, int HD, int RAM, String motherBoard, String videoCard){
        super(old);
        setBrand(brand);
        setProcessor(processor);
        setHD(HD);
        setRAM(RAM);
        setMotherBoard(motherBoard);
        setVideoCard(videoCard);
    }

    public LaptopAndPcCategory(int Amount, double Price, double Rating, int NumberOfRatings,String brand, String processor, int HD, int RAM, String motherBoard, String videoCard){
        super(Amount,Price,Rating,NumberOfRatings);
        setBrand(brand);
        setProcessor(processor);
        setHD(HD);
        setRAM(RAM);
        setMotherBoard(motherBoard);
        setVideoCard(videoCard);
    }



    private void setBrand(String brand) {
        Brand = brand;
    }

    private void setProcessor(String processor) {
        Processor = processor;
    }

    private void setHD(int HD) {
        this.HD = HD;
    }

    private void setRAM(int RAM) {
        this.RAM = RAM;
    }

    private void setMotherBoard(String motherBoard) {
        MotherBoard = motherBoard;
    }

    private void setVideoCard(String videoCard) {
        VideoCard = videoCard;
    }

    public String getBrand() {
        return Brand;
    }

    public String getProcessor() {
        return Processor;
    }

    public int getHD() {
        return HD;
    }

    public int getRAM() {
        return RAM;
    }

    public String getMotherBoard() {
        return MotherBoard;
    }

    public String getVideoCard() {
        return VideoCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        //LaptopAndPcCategory that = (LaptopAndPcCategory) o;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", Brand='" + Brand + '\'' +
                ", Processor='" + Processor + '\'' +
                ", HD=" + HD +
                ", RAM=" + RAM +
                ", MotherBoard='" + MotherBoard + '\'' +
                ", VideoCard='" + VideoCard + '\'';
    }
}
