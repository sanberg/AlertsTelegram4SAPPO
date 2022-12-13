package ru.detmir.sap.job;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Alert {

    @SerializedName("ScenarioId")
    @Expose
    private String scenarioId;
    @SerializedName("RuleId")
    @Expose
    private String ruleId;
    @SerializedName("AdapterType")
    @Expose
    private String adapterType;
    @SerializedName("Channel")
    @Expose
    private String channel;
    @SerializedName("ErrCat")
    @Expose
    private String errCat;


    @SerializedName("ToService")
    @Expose
    private String toService;
    @SerializedName("ScenarioName")
    @Expose
    private String scenarioName;
    @SerializedName("Severity")
    @Expose
    private String severity;
    @SerializedName("Timestamp")
    @Expose
    private String timestamp;
    @SerializedName("MonitoringUrl")
    @Expose
    private String monitoringUrl;
    @SerializedName("MsgId")
    @Expose
    private String msgId;
    @SerializedName("FromService")
    @Expose
    private String fromService;
    @SerializedName("Namespace")
    @Expose
    private String namespace;
    @SerializedName("ErrCode")
    @Expose
    private String errCode;
    @SerializedName("AdapterNamespace")
    @Expose
    private String adapterNamespace;
    @SerializedName("ErrLabel")
    @Expose
    private String errLabel;
    @SerializedName("ErrText")
    @Expose
    private String errText;
    @SerializedName("ChannelService")
    @Expose
    private String channelService;
    @SerializedName("FromParty")
    @Expose
    private String fromParty;
    @SerializedName("ChannelParty")
    @Expose
    private String channelParty;
    @SerializedName("Component")
    @Expose
    private String component;
    @SerializedName("Interface")
    @Expose
    private String _interface;

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public void setAdapterType(String adapterType) {
        this.adapterType = adapterType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getErrCat() {
        return errCat;
    }

    public void setErrCat(String errCat) {
        this.errCat = errCat;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMonitoringUrl() {
        return monitoringUrl;
    }

    public void setMonitoringUrl(String monitoringUrl) {
        this.monitoringUrl = monitoringUrl;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromService() {
        return fromService;
    }

    public void setFromService(String fromService) {
        this.fromService = fromService;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getAdapterNamespace() {
        return adapterNamespace;
    }

    public void setAdapterNamespace(String adapterNamespace) {
        this.adapterNamespace = adapterNamespace;
    }

    public String getErrLabel() {
        return errLabel;
    }

    public void setErrLabel(String errLabel) {
        this.errLabel = errLabel;
    }

    public String getErrText() {
        return errText;
    }

    public void setErrText(String errText) {
        this.errText = errText;
    }

    public String getChannelService() {
        return channelService;
    }

    public void setChannelService(String channelService) {
        this.channelService = channelService;
    }

    public String getFromParty() {
        return fromParty;
    }

    public void setFromParty(String fromParty) {
        this.fromParty = fromParty;
    }

    public String getChannelParty() {
        return channelParty;
    }

    public void setChannelParty(String channelParty) {
        this.channelParty = channelParty;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public String getToService() {
        return toService;
    }

    public void setToService(String toService) {
        this.toService = toService;
    }

    @Override
    public String toString() {
        return "Alert {" +
                "scenarioId='" + scenarioId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", adapterType='" + adapterType + '\'' +
                ", channel='" + channel + '\'' +
                ", errCat='" + errCat + '\'' +
                ", scenarioName='" + scenarioName + '\'' +
                ", severity='" + severity + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", monitoringUrl='" + monitoringUrl + '\'' +
                ", msgId='" + msgId + '\'' +
                ", fromService='" + fromService + '\'' +
                ", namespace='" + namespace + '\'' +
                ", errCode='" + errCode + '\'' +
                ", adapterNamespace='" + adapterNamespace + '\'' +
                ", errLabel='" + errLabel + '\'' +
                ", errText='" + errText + '\'' +
                ", channelService='" + channelService + '\'' +
                ", fromParty='" + fromParty + '\'' +
                ", channelParty='" + channelParty + '\'' +
                ", component='" + component + '\'' +
                ", interface='" + _interface + '\'' +
                '}';
    }

    public String formatAlertText() {
        return "<b>Component:</b> " + component + "\n" +
                "<b>Monitoring URL: </b> " + monitoringUrl + "\n" +
                "<b>Scenario:</b> " + scenarioName + "\n" +
                "<b>To Service:</b> " + toService + "\n" +
                "<b>Interface:</b> " + _interface + "\n" +
                "<b>TimeStamp:</b> " + timestamp + "\n" +
                "<b>MSGID:</b> " + msgId + "\n" +
                "<b>Error Code:</b> " + errCode + "\n" +
                "<b>Error Text: </b> " + Utils.escapeHTML(errText) + "\n" +
                "<b>Channel Party: </b> " + channelParty + "\n" +
                "<b>Channel Service: </b> " + channelService;

    }
}
