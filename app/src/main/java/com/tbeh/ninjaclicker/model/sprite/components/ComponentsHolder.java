package com.tbeh.ninjaclicker.model.sprite.components;

import android.support.annotation.NonNull;

import com.tbeh.ninjaclicker.model.sprite.Sprite;
import com.tbeh.ninjaclicker.model.sprite.components.ai.AiComp;
import com.tbeh.ninjaclicker.model.sprite.components.general.GeneralComp;
import com.tbeh.ninjaclicker.model.sprite.components.graphics.SharedComp;
import com.tbeh.ninjaclicker.model.sprite.components.physics.PhysicsComp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ComponentsHolder implements Map<String, IComponent> {

    private HashMap<String, IComponent> components;

    ComponentsHolder(ArrayList<IComponent> componentList) {
        components = new HashMap<>();
        for(IComponent component : componentList){
            if(component instanceof AiComp){
                components.put(component.getClass().getSuperclass().getSimpleName(), component);
            }
            else{
                components.put(component.getClass().getSimpleName(), component);
            }
        }
    }

    public ComponentsHolder makeCopy () {
        ArrayList<IComponent> componentList = new ArrayList<>();
        if(general() != null){
            componentList.add(general().makeCopy(this));
        }
        if(ai() != null){
            componentList.add(ai().makeCopy(this));
        }
        if(physics() != null){
            componentList.add(physics().makeCopy(this));
        }
        if(shared() != null) {
            componentList.add(shared());
        }
        return new ComponentsHolder(componentList);
    }

    public void initialize (Sprite sprite){
        for (IComponent component : values()){
            component.initialize(sprite);
        }
    }

    public void update(Sprite sprite){
        for (IComponent component : values()){
            component.update(sprite);
        }
    }

    public void replaceAiComponent(AiComp aiComponent){
        components.remove(AiComp.class.getSimpleName());
        components.put(AiComp.class.getSimpleName(), aiComponent);
    }

    public GeneralComp general() {
        return (GeneralComp)components.get(GeneralComp.class.getSimpleName());
    }

    public PhysicsComp physics() {
        return (PhysicsComp) components.get(PhysicsComp.class.getSimpleName());
    }

    public SharedComp shared() {
        return (SharedComp) components.get(SharedComp.class.getSimpleName());
    }

    public AiComp ai() {
        return (AiComp) components.get(AiComp.class.getSimpleName());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(IComponent component : values()){
            sb.append(component.getClass().getSimpleName());
            sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public boolean isEmpty() {
        return components.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return components.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return components.containsValue(value);
    }

    @Override
    public IComponent get(Object key) {
        return components.get(key);
    }

    @Override
    public IComponent put(String key, IComponent value) {
        return components.put(key, value);
    }

    @Override
    public IComponent remove(Object key) {
        return components.remove(key);
    }

    @Override
    public void putAll(Map m) {
    }

    @Override
    public void clear() {
        components.clear();
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return components.keySet();
    }

    @NonNull
    @Override
    public Collection<IComponent> values() {
        return components.values();
    }

    @NonNull
    @Override
    public Set<Entry<String, IComponent>> entrySet() {
        return components.entrySet();
    }

    @Override
    public IComponent getOrDefault(Object key, IComponent defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super IComponent> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super IComponent, ? extends IComponent> function) {

    }

    @Override
    public IComponent putIfAbsent(String key, IComponent value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(String key, IComponent oldValue, IComponent newValue) {
        return false;
    }

    @Override
    public IComponent replace(String key, IComponent value) {
        return null;
    }

    @Override
    public IComponent computeIfAbsent(String key, Function<? super String, ? extends IComponent> mappingFunction) {
        return null;
    }

    @Override
    public IComponent computeIfPresent(String key, BiFunction<? super String, ? super IComponent, ? extends IComponent> remappingFunction) {
        return null;
    }

    @Override
    public IComponent compute(String key, BiFunction<? super String, ? super IComponent, ? extends IComponent> remappingFunction) {
        return null;
    }

    @Override
    public IComponent merge(String key, IComponent value, BiFunction<? super IComponent, ? super IComponent, ? extends IComponent> remappingFunction) {
        return null;
    }
}
