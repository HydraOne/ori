package cn.geny.ori.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * This table stores the Resources used in the system.
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "RESOURCE_")
public class Resource {
    /**
     * The unique identifier of the Resource.
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * The unique identifier of the DELMIA Apriso object for RESOURCE.
     */
    @TableField(value = "FUID")
    private String fuid;

    /**
     * The name of the Resource.
     */
    @TableField(value = "\"NAME\"")
    private String name;

    /**
     * The name of the Resource.
     */
    @TableField(value = "RESOURCENAME")
    private String resourcename;

    /**
     * The type of the Resource.
     */
    @TableField(value = "RESOURCETYPE")
    private BigDecimal resourcetype;

    /**
     * The assignment of the Resource to a class.
     */
    @TableField(value = "RESOURCECLASSID")
    private BigDecimal resourceclassid;

    /**
     * The link to the TEXT table.
     */
    @TableField(value = "TEXTID")
    private BigDecimal textid;

    /**
     * <For future use>
     */
    @TableField(value = "TRACKINGUOMCODE")
    private String trackinguomcode;

    /**
     * <For future use>
     */
    @TableField(value = "MOBILITYFLAG")
    private Short mobilityflag;

    /**
     * The assignment of the Resource to a Department.
     */
    @TableField(value = "DEPARTMENT")
    private String department;

    /**
     * The assignment of the Resource to a Facility.
     */
    @TableField(value = "FACILITY")
    private String facility;

    /**
     * The assignment of the Resource to a Work Center.
     */
    @TableField(value = "WORKCENTER")
    private String workcenter;

    /**
     * <For future use>
     */
    @TableField(value = "AUTOCHARGE")
    private Short autocharge;

    /**
     * <For future use>
     */
    @TableField(value = "STDRATE")
    private BigDecimal stdrate;

    /**
     * <For future use>
     */
    @TableField(value = "EXCLUDEFROMPLANNING")
    private Short excludefromplanning;

    /**
     * The last maintenance date of the Resource.
     */
    @TableField(value = "LASTMAINTENANCEDATE")
    private Date lastmaintenancedate;

    /**
     * The next maintenance date.
     */
    @TableField(value = "NEXTMAINTENANCEDATE")
    private Date nextmaintenancedate;

    /**
     * The assignment to a Production Line.
     */
    @TableField(value = "PRODUCTIONLINE")
    private String productionline;

    /**
     * <For future use>
     */
    @TableField(value = "TRACKATTENDANCEFLAG")
    private Short trackattendanceflag;

    /**
     * <For future use>
     */
    @TableField(value = "TRACKLABORFLAG")
    private Short tracklaborflag;

    /**
     * Determines whether the Resource should have labor tracked against it.
     */
    @TableField(value = "TRACKRESOURCELABORFLAG")
    private Short trackresourcelaborflag;

    /**
     * <For future use>
     */
    @TableField(value = "CAPACITYID")
    private BigDecimal capacityid;

    /**
     * <For future use>
     */
    @TableField(value = "WORKLOADID")
    private BigDecimal workloadid;

    /**
     * <For future use>
     */
    @TableField(value = "COSTID")
    private BigDecimal costid;

    /**
     * <For future use>
     */
    @TableField(value = "PROCUREMENTID")
    private BigDecimal procurementid;

    /**
     * <For future use>
     */
    @TableField(value = "MOBILITYID")
    private BigDecimal mobilityid;

    /**
     * <For future use>
     */
    @TableField(value = "AVAILABILITYID")
    private BigDecimal availabilityid;

    /**
     * <For future use>
     */
    @TableField(value = "OWNERSHIPID")
    private BigDecimal ownershipid;

    /**
     * The link to UnitID.
     */
    @TableField(value = "UNITID")
    private BigDecimal unitid;

    /**
     * <For future use>
     */
    @TableField(value = "SPECIFICATIONID")
    private BigDecimal specificationid;

    /**
     * The current life cycle of the Resource.
     */
    @TableField(value = "LIFECYCLE")
    private BigDecimal lifecycle;

    /**
     * <For future use>
     */
    @TableField(value = "AUTOADJUSTSTATE")
    private Short autoadjuststate;

    /**
     * Determines if the Resource labor record has been modifed by the system to a coupled state (e.g., the modification of the start time is followed by the modification of the end time of the previous state).
     */
    @TableField(value = "COUPLESTATE")
    private Short couplestate;

    /**
     * The calendar that defines the Resource's availability.
     */
    @TableField(value = "CALENDARID")
    private BigDecimal calendarid;

    /**
     * The Pay Rule that will be used to define its schedule.
     */
    @TableField(value = "PAYRULE")
    private String payrule;

    @TableField(value = "WAREHOUSELOCATIONID")
    private Long warehouselocationid;

    /**
     * Indicates if the Machine is a paypoint of the Work Center.
     */
    @TableField(value = "WORKCENTERPAYPOINT")
    private Short workcenterpaypoint;

    /**
     * Indicates if the Machine is a paypoint of the Department.
     */
    @TableField(value = "DEPARTMENTPAYPOINT")
    private Short departmentpaypoint;

    /**
     * Indicates if the Machine is a paypoint of the Facility.
     */
    @TableField(value = "FACILITYPAYPOINT")
    private Short facilitypaypoint;

    /**
     * Indicates if the Machine is the bottleneck, pace, or constraint Machine of the Work Center.
     */
    @TableField(value = "WORKCENTERCONSTRAINT")
    private Short workcenterconstraint;

    /**
     * Indicates if the Machine is the bottleneck, pace, or constraint Machine of the Department.
     */
    @TableField(value = "DEPARTMENTCONSTRAINT")
    private Short departmentconstraint;

    /**
     * Indicates if the Machine is the paypoint of the Production Line.
     */
    @TableField(value = "PRODUCTIONLINEPAYPOINT")
    private Short productionlinepaypoint;

    /**
     * Indicates if the Machine is the constraint on the Production Line.
     */
    @TableField(value = "PRODUCTIONLINECONSTRAINT")
    private Short productionlineconstraint;

    /**
     * The BOM Facility.
     */
    @TableField(value = "PRODUCTBOMFACILITY")
    private String productbomfacility;

    /**
     * The BOM number (BOM version), which allows a product to have multiple product BOMs active at a time.
     */
    @TableField(value = "PRODUCTBOMNUMBER")
    private String productbomnumber;

    /**
     * The column used to group orders (for Services use).
     */
    @TableField(value = "MASTERRESOURCE")
    private Short masterresource;

    /**
     * The identifier of the product.
     */
    @TableField(value = "PRODUCTID")
    private BigDecimal productid;

    /**
     * Links to FlexData
     */
    @TableField(value = "REFERENCEID")
    private BigDecimal referenceid;

    /**
     * The date when the record was last updated
     */
    @TableField(value = "LASTUPDATEON")
    private Date lastupdateon;

    /**
     * The user which last updated the record
     */
    @TableField(value = "LASTUPDATEDBY")
    private String lastupdatedby;

    /**
     * The date the record was created
     */
    @TableField(value = "CREATEDON")
    private Date createdon;

    /**
     * The user who created the record
     */
    @TableField(value = "CREATEDBY")
    private String createdby;

    /**
     * Indicates if the record is active or deleted
     */
    @TableField(value = "ACTIVE")
    private Short active;

    /**
     * The date the record was last deleted
     */
    @TableField(value = "LASTDELETEON")
    private Date lastdeleteon;

    /**
     * The user who last deleted the record
     */
    @TableField(value = "LASTDELETEDBY")
    private String lastdeletedby;

    /**
     * The date the record was last reactivated
     */
    @TableField(value = "LASTREACTIVATEON")
    private Date lastreactivateon;

    /**
     * The user who last reactivated the record
     */
    @TableField(value = "LASTREACTIVATEDBY")
    private String lastreactivatedby;

    /**
     * The archive identifier
     */
    @TableField(value = "ARCHIVEID")
    private BigDecimal archiveid;

    /**
     * The date the record was last archived
     */
    @TableField(value = "LASTARCHIVEON")
    private Date lastarchiveon;

    /**
     * The user who last archived the record
     */
    @TableField(value = "LASTARCHIVEDBY")
    private String lastarchivedby;

    /**
     * The date the record was last restored from archive
     */
    @TableField(value = "LASTRESTOREON")
    private Date lastrestoreon;

    /**
     * The user who last restored the record from archive
     */
    @TableField(value = "LASTRESTOREDBY")
    private String lastrestoredby;

    /**
     * Current version identifier for the row, for detection of concurrency violations
     */
    @TableField(value = "ROWVERSIONSTAMP")
    private BigDecimal rowversionstamp;

    /**
     * The identifier of the receiving location.
     */
    @TableField(value = "RECEIPTLOCATIONID")
    private Long receiptlocationid;

    /**
     * The identifie  of the Putaway location.
     */
    @TableField(value = "PUTAWAYLOCATIONID")
    private Long putawaylocationid;

    /**
     * The identifier fo the calendar used for Maintenance Activities.
     */
    @TableField(value = "MAINTENANCECALENDARID")
    private Long maintenancecalendarid;

    /**
     * Vertical operation range for the Resource.
     */
    @TableField(value = "OPERATINGRANGE")
    private BigDecimal operatingrange;

    /**
     * DELMIA 3DEXPERIENCE Universal Unique ID (Physical ID).
     */
    @TableField(value = "DSID")
    private String dsid;

    /**
     * DELMIA 3DEXPERIENCE Name.
     */
    @TableField(value = "DSNAME")
    private String dsname;
}