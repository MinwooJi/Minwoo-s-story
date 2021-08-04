package com.dima.test.dto;

public class AlgoCategoryCount {

    // USER_ID 
    private String userId;

    // HASH 
    private int hash;

    // STACK_QUEUE 
    private int stackQueue;

    // HEAP 
    private int heap;

    // SORT 
    private int sort;

    // BROUTE_FORCE 
    private int brouteForce;

    // GREEDY 
    private int greedy;

    // DYNAMIC 
    private int dynamic;

    // DFS_BFS 
    private int dfsBfs;

    // BINARY 
    private int binary;

    // GRAPH 
    private int graph;

    // SIMULATION 
    private int simulation;

    // ETC 
    private int etc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public int getStackQueue() {
        return stackQueue;
    }

    public void setStackQueue(int stackQueue) {
        this.stackQueue = stackQueue;
    }

    public int getHeap() {
        return heap;
    }

    public void setHeap(int heap) {
        this.heap = heap;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getBrouteForce() {
        return brouteForce;
    }

    public void setBrouteForce(int brouteForce) {
        this.brouteForce = brouteForce;
    }

    public int getGreedy() {
        return greedy;
    }

    public void setGreedy(int greedy) {
        this.greedy = greedy;
    }

    public int getDynamic() {
        return dynamic;
    }

    public void setDynamic(int dynamic) {
        this.dynamic = dynamic;
    }

    public int getDfsBfs() {
        return dfsBfs;
    }

    public void setDfsBfs(int dfsBfs) {
        this.dfsBfs = dfsBfs;
    }

    public int getBinary() {
        return binary;
    }

    public void setBinary(int binary) {
        this.binary = binary;
    }

    public int getGraph() {
        return graph;
    }

    public void setGraph(int graph) {
        this.graph = graph;
    }

    public int getSimulation() {
        return simulation;
    }

    public void setSimulation(int simulation) {
        this.simulation = simulation;
    }

    public int getEtc() {
        return etc;
    }

    public void setEtc(int etc) {
        this.etc = etc;
    }

    // AlgoCategoryCount 모델 복사
    public void CopyData(AlgoCategoryCount param)
    {
        this.userId = param.getUserId();
        this.hash = param.getHash();
        this.stackQueue = param.getStackQueue();
        this.heap = param.getHeap();
        this.sort = param.getSort();
        this.brouteForce = param.getBrouteForce();
        this.greedy = param.getGreedy();
        this.dynamic = param.getDynamic();
        this.dfsBfs = param.getDfsBfs();
        this.binary = param.getBinary();
        this.graph = param.getGraph();
        this.simulation = param.getSimulation();
        this.etc = param.getEtc();
    }
}