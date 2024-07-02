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

    public int convertirASegundos (Date time) {
        // convertir el tiempo de entrada a String y luego a segundos
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String timeInString = timeFormat.format(time);

        // Divide la cadena para extraer horas, minutos y segundos
        String[] parts = timeInString.split(":");
        int hrs = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        int seg = Integer.parseInt(parts[2]);

        return hrs * 3600 + min * 60 + seg;
    }

    public String whereIsTrain(int trainId, Date time) {
        // compruebo que el tren se encuentre en ruta y este asociada a una linea
        int lineId = -1;
        Date departureTime = null;
        int arrivalStation = -1;
        int departureStation = -1;
        boolean isTrainInRoute = false;
        for (Route route : routes) {
            if (route.getTrainId() == trainId) {
                lineId = route.getLineId();
                departureTime = route.getDepartureTime();
                arrivalStation = route.getArrivalStation();
                departureStation = route.getDepartureStation();
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
        int trainSpeed = -1;
        int trainStationStayTime = -1;
        boolean isTrainInSubway = false;
        for (Train train : trains) {
            if (train.getId() == trainId) {
                trainSpeed = train.getSpeed();
                trainStationStayTime = train.getStationStayTime();
                isTrainInSubway = true;
                break;
            }
        }
        if (!isTrainInSubway) {
            throw new IllegalArgumentException("No se encuentra un Tren asociado a la red de metro.");
        }

        // obtengo el tiempo de entrada en segundos
        int tiempoEntrada = convertirASegundos(time);

        int tiempoSalidaTren = convertirASegundos(departureTime);

        int timeInRoute = tiempoEntrada - tiempoSalidaTren;

        if (timeInRoute <= 0) {
            return "El Tren " + trainId + "aún no comienza sus servicios en la hora indicada";
        }
        int timeAcc = 0;
        String stationName = "";
        boolean start = false;
        for (Line line : lines) {
            if (line.getId() == lineId) {
                for (Section section : line.getSections()) {
                    // comienzo a calcular desde la estacion de partida
                    if (section.getPoint1().getId() == departureStation) {
                        start = true;
                    }
                    // si llego a la estacion de llegada, retorno la ultima estacion
                    if (section.getPoint2().getId() == arrivalStation) {
                        stationName = section.getPoint2().getName();
                        break;
                    }
                    if (start) {
                        timeAcc = timeAcc + trainStationStayTime + section.getPoint1().getStopTime() + section.getDistance() / trainSpeed;
                        if (timeAcc >= timeInRoute) {
                            stationName = section.getPoint1().getName();
                            break;
                        }
                    }
                }
                if (timeAcc >= timeInRoute) {
                    break;
                }
            }
            if (stationName.isEmpty()) {
                stationName = "En tránsito";
            }
        }
        return "El Tren " + trainId + " a las " + time +  " se encuentra en la Estación: " + stationName + " de la Línea: " + lineId;
    }

    public List<String> trainPath (int trainId, Date time) {
        // compruebo que el tren se encuentre en ruta y este asociada a una linea
        int lineId = -1;
        Date departureTime = null;
        int arrivalStation = -1;
        int departureStation = -1;
        boolean isTrainInRoute = false;
        for (Route route : routes) {
            if (route.getTrainId() == trainId) {
                lineId = route.getLineId();
                departureTime = route.getDepartureTime();
                arrivalStation = route.getArrivalStation();
                departureStation = route.getDepartureStation();
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
        int trainSpeed = -1;
        int trainStationStayTime = -1;
        boolean isTrainInSubway = false;
        for (Train train : trains) {
            if (train.getId() == trainId) {
                trainSpeed = train.getSpeed();
                trainStationStayTime = train.getStationStayTime();
                isTrainInSubway = true;
                break;
            }
        }
        if (!isTrainInSubway) {
            throw new IllegalArgumentException("No se encuentra un Tren asociado a la red de metro.");
        }

        // obtengo el tiempo de entrada en segundos
        int tiempoEntrada = convertirASegundos(time);

        int tiempoSalidaTren = convertirASegundos(departureTime);

        int timeInRoute = tiempoEntrada - tiempoSalidaTren;

        if (timeInRoute <= 0) {
            throw new IllegalArgumentException("El tren ya completó su ruta en la hora ingresada.");
        }
        int timeAcc = 0;
        List<String> estaciones = new ArrayList<>();
        boolean start = false;
        for (Line line : lines) {
            if (line.getId() == lineId) {
                List<Section> sections = line.getSections();
                for (int i = 0; i < sections.size(); i++) {
                    Section section = sections.get(i);
                    if (section.getPoint1().getId() == departureStation) {
                        start = true;
                    }
                    // comienzo a agregar estaciones desde la estacion de partida y cuando el tiempo transcurrido sea mayor o igual a la diferencia entre el tiempo ingresado y el tiempo de partida del tren
                    if (timeAcc >= timeInRoute && start) {
                        estaciones.add(section.getPoint1().getName());
                    }
                    // si estoy en la ultima seccion, agrego la estacion faltante del point 2
                    if (i == sections.size() - 1 || section.getPoint1().getId() == arrivalStation) {
                        estaciones.add(section.getPoint2().getName());
                        break;
                    }
                    timeAcc = timeAcc + trainStationStayTime + section.getPoint1().getStopTime() + section.getDistance() / trainSpeed;
                }
            }
        }
        return estaciones;
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
