package org.example;
import java.text.SimpleDateFormat;
import java.util.*;

public class Subway {

    private int id;
    private String name;
    private List<Train> trains;
    private List<Line> lines;
    private List<Driver> drivers;
    private List<Route> routes;

    public Subway(int id, String name) {
        this.id = id;
        this.name = name;
        this.trains = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.routes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train... newTrains) {
        Set<Integer> trainIds = new HashSet<>();

        for (Train train: trains) {
            trainIds.add(train.getId());
        }
        Set<Integer> newTrainIds = new HashSet<>();
        for (Train newTrain: newTrains) {
            if (trainIds.contains(newTrain.getId()) || !newTrainIds.add(newTrain.getId()) || !newTrain.isTrain()) {
                throw new IllegalArgumentException("Tren inválido");
            }
        }
        trains.addAll(List.of(newTrains));
    }

    public void addLine(Line... newLines) {
        Set<Integer> lineIds = new HashSet<>();

        for (Line line: lines) {
            lineIds.add(line.getId());
        }
        Set<Integer> newLineIds = new HashSet<>();
        for (Line newLine: newLines) {
            if (lineIds.contains(newLine.getId()) || !newLineIds.add(newLine.getId()) || !newLine.isLine(newLine)) {
                throw new IllegalArgumentException("Línea inválida");
            }
        }
        lines.addAll(List.of(newLines));
    }

    public void addDriver(Driver... newDrivers) {
        Set<Integer> driverIds = new HashSet<>();

        for (Driver driver: drivers) {
            driverIds.add(driver.getId());
        }
        Set<Integer> newDriverIds = new HashSet<>();
        for (Driver newDriver : newDrivers) {
            if (driverIds.contains(newDriver.getId()) || !newDriverIds.add(newDriver.getId())) {
                throw new IllegalArgumentException("Conductor inválido");
            }
        }
        drivers.addAll(List.of(newDrivers));
    }

    public void assignTrainToLine(int trainId, int lineId) {
        // compruebo que exista el id de la linea ingresado
        Set<Integer> lineIds = new HashSet<>();
        for (Line line : lines) {
            lineIds.add(line.getId());
        }
        if (!lineIds.contains(lineId)) {
            throw new IllegalArgumentException("Línea inválida");
        }
        // compruebo que exista el id del tren ingresado
        Set<Integer> trainIds = new HashSet<>();
        for (Train train : trains) {
            trainIds.add(train.getId());
        }
        if (!trainIds.contains(trainId)) {
            throw new IllegalArgumentException("Tren inválido");
        }
        // asigno el tren a la linea
        routes.add(new Route(trainId, lineId));
    }

    public void assignDriverToTrain(int trainId, int driverId, Date departureTime, int departureStation, int arrivalStation) {

        String trainMaker = "";
        for (Train train : trains) {
            if (train.getId() == trainId) {
                trainMaker = train.getTrainMaker();
            }
        }
        // compruebo que el id del tren exista
        if (trainMaker.isEmpty()) {
            throw new IllegalArgumentException("Tren id inválido");
        }
        String driverTrainMaker = "";
        for (Driver driver : drivers) {
            if (driver.getId() == driverId) {
                driverTrainMaker = driver.getTrainMaker();
            }
        }
        // compruebo que el id del driver exista
        if (driverTrainMaker.isEmpty()) {
            throw new IllegalArgumentException("Driver id inválido");
        }
        // compruebo que el trainMaker sea el mismo
        if (!trainMaker.equals(driverTrainMaker)) {
            throw new IllegalArgumentException("El train maker del tren y del driver no coinciden");
        }

        // asigno drivers a una ruta ya creada previamente en assignTrainToLine
        for (Route route: routes) {
            if (route.getTrainId() == trainId) {
                route.setDriverId(driverId);
                route.setDepartureTime(departureTime);
                route.setDepartureStation(departureStation);
                route.setArrivalStation(arrivalStation);
            }
        }
    }

    public String whereIsTrain(int trainId, Date time) {
        // compruebo que el tren se encuentre en ruta y este asociada a una linea
        int lineId = -1;
        Date departureTime = null;
        int arrivalStation;
        int departureStation;
        boolean isTrainInRoute = false;
        for (Route route : routes) {
            if (route.getTrainId() == trainId) {
                lineId = route.getLineId();
                departureTime = route.getDepartureTime();
                arrivalStation = route.getArrivalStation();
                departureStation = route.getArrivalStation();
                isTrainInRoute = true;
                break;
            }
        }
        if (!isTrainInRoute) {
            throw new IllegalArgumentException("No se encuentra un Tren asociado a una Ruta.");
        } else if (lineId == -1) {
            throw new IllegalArgumentException("No se encuentra una Línea asociada al Tren ingresado.");
        }
        // compruebo que el tren exista en la red de metro
        boolean isTrainInSubway = false;
        for (Train train : trains) {
            if (train.getId() == trainId) {
                isTrainInSubway = true;
                break;
            }
        }
        if (!isTrainInSubway) {
            throw new IllegalArgumentException("No se encuentra un Tren asociado a la red de metro.");
        }
        // convertir el tiempo de entrada a String y luego a segundos
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String timeInString = timeFormat.format(time);

        // Divide la cadena para extraer horas, minutos y segundos
        String[] parts = timeInString.split(":");
        int horas = Integer.parseInt(parts[0]);
        int minutos = Integer.parseInt(parts[1]);
        int segundos = Integer.parseInt(parts[2]);

        int totalSegundos = horas * 3600 + minutos * 60 + segundos;

        // convertir el tiempo de partida de la Ruta a String y luego a segundos
        SimpleDateFormat routeTimeFormat = new SimpleDateFormat("HH:mm:ss");
        String routeTimeInString = timeFormat.format(departureTime);

        // Divide la cadena para extraer horas, minutos y segundos
        String[] routeParts = routeTimeInString.split(":");
        int routeHoras = Integer.parseInt(routeParts[0]);
        int routeMinutos = Integer.parseInt(routeParts[1]);
        int routeSegundos = Integer.parseInt(routeParts[2]);

        int totalRouteSegundos = routeHoras * 3600 + routeMinutos * 60 + routeSegundos;

        int timeInRoute = totalSegundos - totalRouteSegundos;

        if (timeInRoute <= 0) {
            return "El Tren " + trainId + "aún no comienza sus servicios en la hora indicada";
        }
        for (Line line : lines) {
            if (line.getId() == lineId) {
                for (int i = 0; i < line.getSections().size(); i++) {
                    
                }
            }
        }
    }


    @Override
    public String toString() {
        return "*********************************************************************************************************************************************************\n" +
                "*********************************************************************************************************************************************************\n" +
                "Subway: " +
                "id = " + id +
                ", name = '" + name + '\'' +
                "\n*********************************************************************************************************************************************************" +
                "\ntrains = " + trains +
                "\n*********************************************************************************************************************************************************" +
                "\nlines = " + lines +
                "\n*********************************************************************************************************************************************************\n" +
                "\ndrivers = " + drivers +
                "\nroutes = " + routes +
                '}' +
                "\n*********************************************************************************************************************************************************\n" +
                "*********************************************************************************************************************************************************\n";
    }
}
