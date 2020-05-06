export interface ApiCallSagaFunc {
  type: string;
  apiFunc: any;
  successCb?: Function;
  failCb?: Function;
}
