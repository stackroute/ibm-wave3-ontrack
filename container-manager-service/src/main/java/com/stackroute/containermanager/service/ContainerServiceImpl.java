package com.stackroute.containermanager.service;

import com.stackroute.containermanager.domain.*;
import com.stackroute.containermanager.exception.OrderAlreadyExists;
import com.stackroute.containermanager.exception.OrderNotFound;
import com.stackroute.containermanager.repository.ContainerRepository;
import com.stackroute.containermanager.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ContainerServiceImpl implements ContainerService {
    private ContainerRepository containerRepository;
    private SlotRepository slotRepository;

    @Autowired
    public ContainerServiceImpl(ContainerRepository containerRepository, SlotRepository slotRepository) {
        this.containerRepository = containerRepository;
        this.slotRepository = slotRepository;

    }

    @Override
    public SlotEvaluation saveOrderAndGetSlots(Order order) throws OrderAlreadyExists {
        if (containerRepository.existsById(order.getResponseId())) {
            throw new OrderAlreadyExists("Order already exists");
        }
        Order savedOrder = containerRepository.save(order);
        if (savedOrder == null) {
            throw new OrderAlreadyExists();
        }

        return getSlot(order);
//        return savedOrder;
        // getSlots();
    }

    public SlotEvaluation getSlot(Order order) {

        if (slotRepository.existsById(order.getOrderDate())) {
            Optional<SlotEvaluation> slotEvaluation = slotRepository.findById(order.getOrderDate());
            List<SlotAvailability> slotAvailabilities = slotEvaluation.get().getSlotAvailabilities();
            for (SlotAvailability slotAvailability : slotAvailabilities) {
                String slotDate = slotAvailability.getDate();
                List<Slot> slots = slotAvailability.getSlots();
                for (Slot slot : slots) {
                    slot.getSlotType();
                    slot.getAvailableContainer();
                    if (slot.getAvailableContainer() >= order.getProductList().size() * 20) {
                        //slot.setCost(slot.getCost()+10);
                        if (order.getProductList().size() * 20 == 100) {
                            slot.setCost(10);
                        } else if (order.getProductList().size() * 20 == 80) {
                            slot.setCost(20);
                        } else if (order.getProductList().size() * 20 == 60) {
                            slot.setCost(40);
                        } else if (order.getProductList().size() * 20 == 40) {
                            slot.setCost(60);
                        } else if (order.getProductList().size() * 20 == 20) {
                            slot.setCost(80);
                        }
                        slot.setSlotStatus(true);
                    } else {
                        slot.setSlotStatus(false);
                    }
                }
                slotAvailability.setSlots(slots);
                slotEvaluation.get().setSlotAvailabilities(slotAvailabilities);
            }
            return slotRepository.save(slotEvaluation.get());
//            List<Slot> slots=slotAvailabilities.get().getSlots();
        } else {
            SlotEvaluation slotEvaluation = new SlotEvaluation();
            slotEvaluation.setOrderId(order.getOrderId());
            slotEvaluation.setOrderDate(order.getOrderDate());
            List<SlotAvailability> slotAvailabilities = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SlotAvailability slotAvailability = new SlotAvailability();
                if (i == 0) {
                    slotAvailability.setDate(order.getOrderDate());
                    slotAvailabilities.add(slotAvailability);
                    List<Slot> slots = new ArrayList<>();
                    for (int j = 0; j < 3; j++) {
                        Slot slot = new Slot();
                        if (j == 0) {
                            String slotType = "A";
                            slot.setSlotStatus(true);
                            slot.setCost(10);
                            slot.setSlotType("A");
                            slot.setAvailableContainerSize(100);
                            slots.add(slot);
                        }
                        if (j == 1) {
                            String slotType = "B";
                            slot.setSlotStatus(true);
                            slot.setCost(10);
                            slot.setSlotType("B");
                            slot.setAvailableContainerSize(100);
                            slots.add(slot);
                        }
                        if (j == 2) {
                            String slotType = "B";
                            slot.setSlotStatus(true);
                            slot.setCost(10);
                            slot.setSlotType("B");
                            slot.setAvailableContainerSize(100);
                            slots.add(slot);
                        }
                    }
                    slotAvailability.setSlots(slots);
                    slotEvaluation.setSlotAvailabilities(slotAvailabilities);
                }
                if (i == 1) {

//                    slotAvailability.setDate(order.getOrderDate().plusDays(1));
                    slotAvailability.setDate("2019/03/5");
                    slotAvailabilities.add(slotAvailability);
                    List<Slot> slots = new ArrayList<>();
                    for (int j = 0; j < 3; j++) {
                        Slot slot = new Slot();
                        if (j == 0) {
                            String slotType = "A";
                            slot.setSlotStatus(true);
                            slot.setCost(5);
                            slot.setSlotType("A");
                            slot.setAvailableContainerSize(100);
                            slots.add(slot);
                        }
                        if (j == 1) {
                            String slotType = "B";
                            slot.setSlotStatus(true);
                            slot.setCost(5);
                            slot.setSlotType("B");
                            slot.setAvailableContainerSize(100);
                            slots.add(slot);
                        }
                        if (j == 2) {
                            String slotType = "B";
                            slot.setSlotStatus(true);
                            slot.setCost(5);
                            slot.setSlotType("B");
                            slot.setAvailableContainerSize(100);
                            slots.add(slot);
                        }
                    }
                    slotAvailability.setSlots(slots);
                }
                slotEvaluation.setSlotAvailabilities(slotAvailabilities);
            }
            return slotRepository.save(slotEvaluation);
        }

    }

    public SlotEvaluation saveSelecteSlots(SelectedSlot selectedSlot) throws OrderNotFound, OrderAlreadyExists {

        if (slotRepository.existsById(selectedSlot.getOrderDate())) {
            System.out.println("inside if");
            Optional<SlotEvaluation> slotEvaluation = slotRepository.findById(selectedSlot.getOrderDate());
            List<SlotAvailability> slotAvailabilities = slotEvaluation.get().getSlotAvailabilities();
            System.out.println("slotavailabiloity data+++++++++++++++++++++" + slotAvailabilities);
            for (SlotAvailability slotAvailability : slotAvailabilities) {
                String slotDate = slotAvailability.getDate();
//                List<Slot> slots = slotAvailability.getSlots();
                if (slotDate.equals(slotAvailability.getDate())) {
                    List<Slot> slots = slotAvailability.getSlots();
                    for (Slot slot : slots) {
                        if (selectedSlot.getSlotType().equals(slot.getSlotType())) {
                            int selectedWeight = selectedSlot.getProductList().size() * 20;
                            slot.setAvailableContainerSize(slot.getAvailableContainerSize() - selectedWeight);
                        }

                    }
                }

                return slotRepository.save(slotEvaluation.get());

            }

        }
        return null;
    }
    }