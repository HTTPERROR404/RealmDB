# RealmDB
Repository for RealmDB related R&amp;D
This POC helps in finding out the time taken by RealmDB for processing huge data.

For instance, in a go, it inserts 10K data in realm.

On every app cycle, this inserts 30K data.

Time taken to query the changes is also recorded and printed in logs

Sample Logs : 

RealmPOC: Time Difference 1 insert 10006 items 2321 ms
RealmPOC: Time Difference 2 insert 10006 items 2194 ms
RealmPOC: Time Difference Fetch all (total - 90054) data 176 ms
RealmPOC: Time Differecnce search query (count 89523) 65 ms

For better understanding, please check logs.
