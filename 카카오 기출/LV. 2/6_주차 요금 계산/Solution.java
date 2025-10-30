import java.util.*;

class Car {
    private int carNumber, parkingPrice;
    
    public Car(String carNumber, int parkingPrice) {
        this.carNumber = Integer.parseInt(carNumber);
        this.parkingPrice = parkingPrice;
    }
    
    public int getCarNumber() {
        return carNumber;
    }
    
    public int getParkingPrice() {
        return parkingPrice;
    }
}

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> parkingLot = new HashMap<>();
        Map<String, Integer> parkingTimeMap = new HashMap<>();
        for (String record : records) {
            String[] info = record.split(" ");
            String time = info[0], carNumber = info[1], inOrOut = info[2];
            if (inOrOut.equals("IN")) {
                parkingLot.put(carNumber, time);
            } else {
                String[] inTimeInfo = parkingLot.get(carNumber).split(":");
                int inTime = Integer.parseInt(inTimeInfo[0]);
                int inMinute = Integer.parseInt(inTimeInfo[1]);
                
                String[] outTimeInfo = time.split(":");
                int outTime = Integer.parseInt(outTimeInfo[0]) - 1;
                int outMinute = Integer.parseInt(outTimeInfo[1]) + 60;
                
                int totalMinute = (outTime - inTime) * 60 + (outMinute - inMinute);
                parkingTimeMap.put(carNumber, parkingTimeMap.getOrDefault(carNumber, 0) + totalMinute);
                parkingLot.remove(carNumber);
            }
        }
        
        for (String carNumber : parkingLot.keySet()) {
            String[] inTimeInfo = parkingLot.get(carNumber).split(":");
            int inTime = Integer.parseInt(inTimeInfo[0]);
            int inMinute = Integer.parseInt(inTimeInfo[1]);
            
            int totalMinute = (23 - inTime) * 60 + (59 - inMinute);
            parkingTimeMap.put(carNumber, parkingTimeMap.getOrDefault(carNumber, 0) + totalMinute);
        }
        
        int defaultTime = fees[0], defaultPrice = fees[1], unitTime = fees[2], unitPrice = fees[3];
        Car[] cars = new Car[parkingTimeMap.keySet().size()];
        int idx = 0;
        Map<String, Integer> parkingPriceMap = new HashMap<>();
        for (String carNumber : parkingTimeMap.keySet()) {
            int parkingTime = parkingTimeMap.get(carNumber);
            
            parkingTime -= defaultTime;
            if (parkingTime < 0) {
                parkingPriceMap.put(carNumber, defaultPrice);
                cars[idx++] = new Car(carNumber, defaultPrice);
                continue;
            }
            
            int parkingPrice = defaultPrice + (parkingTime % unitTime == 0 ? parkingTime / unitTime : parkingTime / unitTime + 1) * unitPrice;
            parkingPriceMap.put(carNumber, parkingPrice);
            cars[idx++] = new Car(carNumber, parkingPrice);
        }
        
        Arrays.sort(cars, (o1, o2) -> Integer.compare(o1.getCarNumber(), o2.getCarNumber()));
        int[] answer = new int[cars.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = cars[i].getParkingPrice();
        }
        return answer;
    }
}
