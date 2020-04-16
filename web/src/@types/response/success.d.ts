export interface HTTPResponse<T> {
  status: number;
  data: T;
}
