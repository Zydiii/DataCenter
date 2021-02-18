package zyd.datacenter.Entities.Weapon;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Document(value = "Weapons")
public class Weapon {
    @Id
    private String id;

    private String weaponName; // 武器名

    private File file; // 模型文件

    private float maxWeight; // 最大重量

    private float maxVelocity; // 最大速度

    private float oilStorage; // 储油量

    private float missileStorage; // 储弹量

    private float health; // 生命值

    private float lockLevel; // 可以获取的等级限制

    public Weapon(String weaponName, File file, float maxWeight, float maxVelocity, float oilStorage, float missileStorage, float health, float lockLevel) {
        this.weaponName = weaponName;
        this.file = file;
        this.maxWeight = maxWeight;
        this.maxVelocity = maxVelocity;
        this.oilStorage = oilStorage;
        this.missileStorage = missileStorage;
        this.health = health;
        this.lockLevel = lockLevel;
    }

    public Weapon(String weaponName, float maxWeight, float maxVelocity, float oilStorage, float missileStorage, float health, float lockLevel) {
        this.weaponName = weaponName;
        this.file = file;
        this.maxWeight = maxWeight;
        this.maxVelocity = maxVelocity;
        this.oilStorage = oilStorage;
        this.missileStorage = missileStorage;
        this.health = health;
        this.lockLevel = lockLevel;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public float getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public float getOilStorage() {
        return oilStorage;
    }

    public void setOilStorage(float oilStorage) {
        this.oilStorage = oilStorage;
    }

    public float getMissileStorage() {
        return missileStorage;
    }

    public void setMissileStorage(float missileStorage) {
        this.missileStorage = missileStorage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getLockLevel() {
        return lockLevel;
    }

    public void setLockLevel(float lockLevel) {
        this.lockLevel = lockLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
}
